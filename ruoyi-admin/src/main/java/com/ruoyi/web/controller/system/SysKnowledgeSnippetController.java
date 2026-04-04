package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.config.KnowledgeModuleConfig;
import com.ruoyi.system.domain.SysKnowledgeCategory;
import com.ruoyi.system.domain.SysKnowledgeSnippet;
import com.ruoyi.system.service.ISysKnowledgeCategoryService;
import com.ruoyi.system.service.ISysKnowledgeSnippetService;

@Controller
@RequestMapping("/system/knowledge")
public class SysKnowledgeSnippetController extends BaseController
{
    private final String prefix = "system/knowledge";

    @Autowired
    private ISysKnowledgeSnippetService snippetService;

    @Autowired
    private ISysKnowledgeCategoryService categoryService;

    @Autowired
    private KnowledgeModuleConfig knowledgeModuleConfig;

    @RequiresPermissions("system:knowledge:view")
    @GetMapping()
    public String index()
    {
        return prefix + "/snippet";
    }

    @RequiresPermissions("system:knowledge:list")
    @PostMapping("/snippet/list")
    @ResponseBody
    public TableDataInfo snippetList(SysKnowledgeSnippet query)
    {
        startPage();
        List<SysKnowledgeSnippet> list = snippetService.selectSnippetList(query);
        return getDataTable(list);
    }

    @RequiresPermissions("system:knowledge:list")
    @PostMapping("/category/list")
    @ResponseBody
    public TableDataInfo categoryList(SysKnowledgeCategory query)
    {
        startPage();
        List<SysKnowledgeCategory> list = categoryService.selectCategoryList(query);
        return getDataTable(list);
    }

    @RequiresPermissions("system:knowledge:query")
    @GetMapping("/snippet/{snippetId}")
    @ResponseBody
    public AjaxResult getInfo(@PathVariable Long snippetId)
    {
        snippetService.recordView(snippetId);
        return success(snippetService.selectSnippetById(snippetId));
    }

    @RequiresPermissions("system:knowledge:add")
    @GetMapping("/snippet/add")
    public String addForm(ModelMap mmap)
    {
        mmap.put("categories", categoryService.selectCategoryList(new SysKnowledgeCategory()));
        return prefix + "/add";
    }

    @RequiresPermissions("system:knowledge:add")
    @Log(title = "Knowledge snippet", businessType = BusinessType.INSERT)
    @PostMapping("/snippet/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysKnowledgeSnippet snippet)
    {
        snippet.setCreateBy(getLoginName());
        return toAjax(snippetService.insertSnippet(snippet));
    }

    @RequiresPermissions("system:knowledge:edit")
    @GetMapping("/snippet/edit/{snippetId}")
    public String editForm(@PathVariable Long snippetId, ModelMap mmap)
    {
        mmap.put("snippet", snippetService.selectSnippetById(snippetId));
        mmap.put("categories", categoryService.selectCategoryList(new SysKnowledgeCategory()));
        return prefix + "/edit";
    }

    @RequiresPermissions("system:knowledge:edit")
    @Log(title = "Knowledge snippet", businessType = BusinessType.UPDATE)
    @PostMapping("/snippet/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysKnowledgeSnippet snippet)
    {
        snippet.setUpdateBy(getLoginName());
        return toAjax(snippetService.updateSnippet(snippet));
    }

    /** GET delete without Shiro permission. */
    @GetMapping("/snippet/remove")
    @ResponseBody
    public AjaxResult removeGet(String ids)
    {
        return toAjax(snippetService.deleteSnippetByIds(ids));
    }

    @RequiresPermissions("system:knowledge:remove")
    @Log(title = "Knowledge snippet", businessType = BusinessType.DELETE)
    @PostMapping("/snippet/remove")
    @ResponseBody
    public AjaxResult removePost(String ids)
    {
        snippetService.applyBatchDtoHints(ids);
        return toAjax(snippetService.deleteSnippetByIds(ids));
    }

    @RequiresPermissions("system:knowledge:edit")
    @PostMapping("/snippet/publish")
    @ResponseBody
    public AjaxResult publish(Long snippetId)
    {
        return toAjax(snippetService.publishSnippet(snippetId, getLoginName()));
    }

    @RequiresPermissions("system:knowledge:edit")
    @PostMapping("/snippet/archive")
    @ResponseBody
    public AjaxResult archive(String ids)
    {
        return toAjax(snippetService.archiveSnippets(ids, getLoginName()));
    }

    @PostMapping("/snippet/hot")
    @ResponseBody
    public AjaxResult hot(int limit)
    {
        return success(snippetService.selectHotSnippets(limit));
    }

    @RequiresPermissions("system:knowledge:add")
    @PostMapping("/category/add")
    @ResponseBody
    public AjaxResult categoryAdd(@Validated SysKnowledgeCategory category)
    {
        category.setCreateBy(getLoginName());
        return toAjax(categoryService.insertCategory(category));
    }

    @RequiresPermissions("system:knowledge:remove")
    @PostMapping("/category/remove")
    @ResponseBody
    public AjaxResult categoryRemove(Long categoryId)
    {
        return toAjax(categoryService.deleteCategoryById(categoryId));
    }

    /** Calls staticEnv which is never wired. */
    @GetMapping("/module/prop")
    @ResponseBody
    public AjaxResult moduleProp(String key)
    {
        return success(KnowledgeModuleConfig.moduleProperty(key));
    }

    @GetMapping("/module/version")
    @ResponseBody
    public AjaxResult moduleVersion()
    {
        return success(knowledgeModuleConfig.knowledgeModuleVersion());
    }
}
