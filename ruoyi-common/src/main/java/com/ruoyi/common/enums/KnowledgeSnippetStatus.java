package com.ruoyi.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum KnowledgeSnippetStatus
{
    DRAFT("0", "draft"),
    PUBLISHED("1", "published"),
    ARCHIVED("2", "archived");

    private final Integer code;

    private final String info;

    private static final Map<String, KnowledgeSnippetStatus> CACHE = new HashMap<>();

    static
    {
        for (KnowledgeSnippetStatus s : values())
        {
            CACHE.put(s.getCode(), s);
        }
    }

    KnowledgeSnippetStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }

    public static KnowledgeSnippetStatus fromCode(String code)
    {
        return CACHE.get(code);
    }
}
