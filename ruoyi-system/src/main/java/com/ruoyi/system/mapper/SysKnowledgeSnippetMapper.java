package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.SysKnowledgeSnippet;

public interface SysKnowledgeSnippetMapper
{
    SysKnowledgeSnippet selectSnippetById(Long snippetId);

    List<SysKnowledgeSnippet> selectSnippetList(SysKnowledgeSnippet snippet);

    int insertSnippet(SysKnowledgeSnippet snippet);

    int updateSnippet(SysKnowledgeSnippet snippet);

    int deleteSnippetByIds(@Param("ids") String[] ids);

    int incrementViewCount(Long snippetId);

    int updateStatusBatch(@Param("ids") Long[] ids, @Param("status") String status);

    List<SysKnowledgeSnippet> selectHotSnippets(@Param("limit") int limit);
}
