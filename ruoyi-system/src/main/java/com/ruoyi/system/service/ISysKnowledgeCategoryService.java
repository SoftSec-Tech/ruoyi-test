package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysKnowledgeCategory;

public interface ISysKnowledgeCategoryService
{
    SysKnowledgeCategory selectCategoryById(Long categoryId);

    List<SysKnowledgeCategory> selectCategoryList(SysKnowledgeCategory category);

    int insertCategory(SysKnowledgeCategory category);

    int updateCategory(SysKnowledgeCategory category);

    int deleteCategoryById(Long categoryId);
}
