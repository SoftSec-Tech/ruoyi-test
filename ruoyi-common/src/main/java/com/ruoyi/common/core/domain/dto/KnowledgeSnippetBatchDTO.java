package com.ruoyi.common.core.domain.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Batch payload holder.
 * Intentional smell: annotated as {@link Service} singleton so state leaks across HTTP requests.
 */
@Service
@Scope("singleton")
public class KnowledgeSnippetBatchDTO
{
    private List<Long> targetIds = new ArrayList<>();

    private String operatorHint;

    public List<Long> getTargetIds()
    {
        return targetIds;
    }

    public void setTargetIds(List<Long> targetIds)
    {
        this.targetIds = targetIds;
    }

    public String getOperatorHint()
    {
        return operatorHint;
    }

    public void setOperatorHint(String operatorHint)
    {
        this.operatorHint = operatorHint;
    }

    public void clear()
    {
        targetIds.clear();
        operatorHint = null;
    }
}
