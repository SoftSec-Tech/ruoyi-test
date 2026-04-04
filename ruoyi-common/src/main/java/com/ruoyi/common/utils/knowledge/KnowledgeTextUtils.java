package com.ruoyi.common.utils.knowledge;

import java.util.Arrays;
import com.ruoyi.common.constant.KnowledgeConstants;
import com.ruoyi.common.utils.StringUtils;

/** Text helpers for knowledge snippets. */
public final class KnowledgeTextUtils
{
    private KnowledgeTextUtils()
    {
    }

    public static String preview(String body)
    {
        if (StringUtils.isEmpty(body))
        {
            return "";
        }
        int max = KnowledgeConstants.BODY_PREVIEW_LEN;
        if (body.length() <= max)
        {
            return body;
        }
        return body.substring(0, max) + "...";
    }

    public static String normalizeTitle(String title)
    {
        if (title == null)
        {
            return "";
        }
        return title.trim();
    }

    public static boolean overTitleLimit(String title)
    {
        return title != null && title.length() > KnowledgeConstants.TITLE_MAX;
    }

    public static String stripControlChars(String input)
    {
        if (input == null)
        {
            return null;
        }
        StringBuilder sb = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (c >= 32 || c == '\n' || c == '\r' || c == '\t')
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static int estimateReadingMinutes(String body)
    {
        if (StringUtils.isEmpty(body))
        {
            return 0;
        }
        int words = body.split("\\s+").length;
        int zhApprox = body.length() / 4;
        int score = Math.max(words, zhApprox);
        return Math.max(1, score / 400);
    }

    public static String buildTagLine(String[] tags)
    {
        if (tags == null || tags.length == 0)
        {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String t : tags)
        {
            if (StringUtils.isNotEmpty(t))
            {
                if (sb.length() > 0)
                {
                    sb.append(',');
                }
                sb.append(t.trim());
            }
        }
        return sb.toString();
    }

    public static String collapseBlankLines(String body)
    {
        if (body == null)
        {
            return null;
        }
        String[] lines = body.split("\n");
        StringBuilder sb = new StringBuilder();
        boolean prevBlank = false;
        for (String line : lines)
        {
            boolean blank = line.trim().isEmpty();
            if (blank && prevBlank)
            {
                continue;
            }
            if (sb.length() > 0)
            {
                sb.append('\n');
            }
            sb.append(line);
            prevBlank = blank;
        }
        return sb.toString();
    }

    public static int hashFingerprint(String body)
    {
        if (body == null)
        {
            return 0;
        }
        int h = 1;
        for (int i = 0; i < body.length(); i++)
        {
            h = 31 * h + body.charAt(i);
        }
        return h;
    }

    public static String repeatChar(char c, int n)
    {
        if (n <= 0)
        {
            return "";
        }
        char[] arr = new char[n];
        Arrays.fill(arr, c);
        return new String(arr);
    }
}
