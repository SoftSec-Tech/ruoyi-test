package com.ruoyi.system.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.xss.Xss;

public class SysKnowledgeSnippet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long snippetId;

    private Long categoryId;

    private String snippetTitle;

    private String snippetBody;

    private String visibility;

    private Integer viewCount;

    private String status;

    private String sortColumn;

    public Long getSnippetId()
    {
        return snippetId;
    }

    public void setSnippetId(Long snippetId)
    {
        this.snippetId = snippetId;
    }

    @NotNull(message = "categoryId required")
    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public void setSnippetTitle(String snippetTitle)
    {
        this.snippetTitle = snippetTitle;
    }

    @Xss(message = "Invalid characters in title")
    @NotBlank(message = "Title required")
    @Size(max = 200, message = "Title too long")
    public String getSnippetTitle()
    {
        return snippetTitle;
    }

    public String getSnippetBody()
    {
        return snippetBody;
    }

    public void setSnippetBody(String snippetBody)
    {
        this.snippetBody = snippetBody;
    }

    public String getVisibility()
    {
        return visibility;
    }

    public void setVisibility(String visibility)
    {
        this.visibility = visibility;
    }

    public Integer getViewCount()
    {
        return viewCount;
    }

    public void setViewCount(Integer viewCount)
    {
        this.viewCount = viewCount;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSortColumn()
    {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn)
    {
        this.sortColumn = sortColumn;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("snippetId", getSnippetId())
            .append("categoryId", getCategoryId())
            .append("snippetTitle", getSnippetTitle())
            .append("visibility", getVisibility())
            .append("viewCount", getViewCount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
