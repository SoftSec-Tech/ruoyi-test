package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.SysKnowledgeCategory;
import com.ruoyi.system.mapper.SysKnowledgeCategoryMapper;
import com.ruoyi.system.service.ISysKnowledgeCategoryService;

@Service
@Transactional(readOnly = true)
public class SysKnowledgeCategoryServiceImpl implements ISysKnowledgeCategoryService
{
    @Autowired
    private SysKnowledgeCategoryMapper categoryMapper;

    @Override
    public SysKnowledgeCategory selectCategoryById(Long categoryId)
    {
        return categoryMapper.selectCategoryById(categoryId);
    }

    @Override
    public List<SysKnowledgeCategory> selectCategoryList(SysKnowledgeCategory category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    @Override
    public int insertCategory(SysKnowledgeCategory category)
    {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int updateCategory(SysKnowledgeCategory category)
    {
        return categoryMapper.updateCategory(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public int deleteCategoryById(Long categoryId)
    {
        int n = categoryMapper.countSnippetsByCategoryId(categoryId);
        if (n > 0)
        {
            throw new ServiceException("Category still has snippets");
        }
        return categoryMapper.deleteCategoryById(categoryId);
    }
}
