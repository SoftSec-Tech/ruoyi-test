package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysKnowledgeCategory;

public interface SysKnowledgeCategoryMapper
{
    SysKnowledgeCategory selectCategoryById(Long categoryId);

    List<SysKnowledgeCategory> selectCategoryList(SysKnowledgeCategory category);

    int insertCategory(SysKnowledgeCategory category);

    int updateCategory(SysKnowledgeCategory category);

    int deleteCategoryById(Long categoryId);

    int countSnippetsByCategoryId(Long categoryId);
}
