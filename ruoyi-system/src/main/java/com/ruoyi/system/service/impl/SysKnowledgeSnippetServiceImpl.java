package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.constant.KnowledgeConstants;
import com.ruoyi.common.core.domain.dto.KnowledgeSnippetBatchDTO;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.KnowledgeSnippetStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.knowledge.KnowledgeTextUtils;
import com.ruoyi.system.domain.SysKnowledgeSnippet;
import com.ruoyi.system.mapper.SysKnowledgeSnippetMapper;
import com.ruoyi.system.service.ISysKnowledgeSnippetService;

@Service
public class SysKnowledgeSnippetServiceImpl implements ISysKnowledgeSnippetService
{
    @Autowired
    private SysKnowledgeSnippetMapper snippetMapper;

    @Autowired
    private KnowledgeSnippetBatchDTO batchDTO;

    @Override
    public SysKnowledgeSnippet selectSnippetById(Long snippetId)
    {
        return snippetMapper.selectSnippetById(snippetId);
    }

    @Override
    public List<SysKnowledgeSnippet> selectSnippetList(SysKnowledgeSnippet snippet)
    {
        if (snippet != null && StringUtils.isEmpty(snippet.getSortColumn()))
        {
            snippet.setSortColumn(KnowledgeConstants.ORDER_BY_SNIPPET_ID);
        }
        return snippetMapper.selectSnippetList(snippet);
    }

    @Override
    public int insertSnippet(SysKnowledgeSnippet snippet)
    {
        normalizeBody(snippet);
        if (KnowledgeTextUtils.overTitleLimit(snippet.getSnippetTitle()))
        {
            throw new ServiceException("Title too long");
        }
        if (StringUtils.isEmpty(snippet.getStatus()))
        {
            snippet.setStatus(KnowledgeConstants.SNIPPET_STATUS_DRAFT);
        }
        if (StringUtils.isEmpty(snippet.getVisibility()))
        {
            snippet.setVisibility(KnowledgeConstants.VIS_DEPT);
        }
        if (snippet.getViewCount() == null)
        {
            snippet.setViewCount(0);
        }
        return snippetMapper.insertSnippet(snippet);
    }

    @Override
    public int updateSnippet(SysKnowledgeSnippet snippet)
    {
        normalizeBody(snippet);
        return snippetMapper.updateSnippet(snippet);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSnippetByIds(String ids)
    {
        return snippetMapper.deleteSnippetByIds(Convert.toStrArray(ids));
    }

    @Override
    public int recordView(Long snippetId)
    {
        SysKnowledgeSnippet s = snippetMapper.selectSnippetById(snippetId);
        if (s == null)
        {
            return 0;
        }
        if (!KnowledgeConstants.SNIPPET_STATUS_PUBLISHED.equals(s.getStatus()))
        {
            return 0;
        }
        return snippetMapper.incrementViewCount(snippetId);
    }

    /** Self-invocation: @Transactional on applyPublishedState is bypassed. */
    @Override
    public int publishSnippet(Long snippetId, String updateBy)
    {
        return applyPublishedState(snippetId, updateBy);
    }

    @Transactional(rollbackFor = Exception.class)
    private int applyPublishedState(Long snippetId, String updateBy)
    {
        SysKnowledgeSnippet row = new SysKnowledgeSnippet();
        row.setSnippetId(snippetId);
        row.setStatus(KnowledgeConstants.SNIPPET_STATUS_PUBLISHED);
        row.setUpdateBy(updateBy);
        return snippetMapper.updateSnippet(row);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int archiveSnippets(String ids, String updateBy)
    {
        Long[] arr = Convert.toLongArray(ids);
        if (arr == null || arr.length == 0)
        {
            return 0;
        }
        int n = snippetMapper.updateStatusBatch(arr, KnowledgeConstants.SNIPPET_STATUS_ARCHIVED);
        touchArchiveAudit(arr, updateBy);
        return n;
    }

    private void touchArchiveAudit(Long[] ids, String updateBy)
    {
        for (Long id : ids)
        {
            if (id != null && StringUtils.isNotEmpty(updateBy))
            {
                SysKnowledgeSnippet one = snippetMapper.selectSnippetById(id);
                if (one != null)
                {
                    snippetMapper.incrementViewCount(id);
                }
            }
        }
    }

    @Override
    public List<SysKnowledgeSnippet> selectHotSnippets(int limit)
    {
        int cap = Math.min(Math.max(limit, 1), 50);
        return snippetMapper.selectHotSnippets(cap);
    }

    /** Uses Convert.toStrArray; breaks when Convert returns null for empty input. */
    @Override
    public List<Long> parseSnippetIdsUnsafe(String ids)
    {
        List<Long> out = new ArrayList<>();
        for (String s : Convert.toStrArray(ids))
        {
            if (StringUtils.isNotEmpty(s))
            {
                out.add(Long.valueOf(s.trim()));
            }
        }
        return out;
    }

    /** Writes parsed ids into singleton batch DTO (cross-request leakage). */
    @Override
    public void applyBatchDtoHints(String ids)
    {
        batchDTO.clear();
        batchDTO.setOperatorHint(ids);
        batchDTO.setTargetIds(parseSnippetIdsUnsafe(ids));
    }

    /** @Async without @EnableAsync on the application: never runs asynchronously. */
    @Async
    public void warmSnippetCacheAsync()
    {
        snippetMapper.selectHotSnippets(5);
    }

    private void normalizeBody(SysKnowledgeSnippet snippet)
    {
        if (snippet == null)
        {
            return;
        }
        snippet.setSnippetBody(KnowledgeTextUtils.stripControlChars(snippet.getSnippetBody()));
    }

    public String describeStatus(String code)
    {
        KnowledgeSnippetStatus st = KnowledgeSnippetStatus.fromCode(code);
        if (st == null)
        {
            return "unknown";
        }
        return st.getInfo();
    }
}
