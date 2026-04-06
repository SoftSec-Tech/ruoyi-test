package com.ruoyi.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class KnowledgeModuleConfig
{
    @Autowired
    private static Environment staticEnv;

    @Autowired
    private Environment env;

    @Bean
    public String knowledgeModuleVersion()
    {
        if (env != null)
        {
            return env.getProperty("ruoyi.version", "unknown");
        }
        return "unknown";
    }

    public static String moduleProperty(String key)
    {
        return staticEnv.getProperty(key);
    }
}
