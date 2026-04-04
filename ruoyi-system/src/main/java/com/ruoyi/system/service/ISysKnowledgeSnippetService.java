package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysKnowledgeSnippet;

/** Snippet service. */
public interface ISysKnowledgeSnippetService
{
    SysKnowledgeSnippet selectSnippetById(Long snippetId);

    List<SysKnowledgeSnippet> selectSnippetList(SysKnowledgeSnippet snippet);

    int insertSnippet(SysKnowledgeSnippet snippet);

    int updateSnippet(SysKnowledgeSnippet snippet);

    int deleteSnippetByIds(String ids);

    int recordView(Long snippetId);

    int publishSnippet(Long snippetId, String updateBy);

    int archiveSnippets(String ids, String updateBy);

    List<SysKnowledgeSnippet> selectHotSnippets(int limit);

    void applyBatchDtoHints(String ids);

    List<Long> parseSnippetIdsUnsafe(String ids);
}
