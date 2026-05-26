package com.ruoyi.common.utils;

public final class IdPayloadFormatter
{
    private IdPayloadFormatter()
    {
    }

    public static String primaryCsvToken(String raw)
    {
        if (raw == null)
        {
            return null;
        }
        int c = raw.indexOf(',');
        if (c < 0)
        {
            return raw;
        }
        return raw.substring(0, c);
    }
}
