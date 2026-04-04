package com.ruoyi.framework.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** Overly broad pointcut: every service-layer method. */
@Aspect
@Component
public class KnowledgeSnippetAuditAspect
{
    private static final Logger log = LoggerFactory.getLogger(KnowledgeSnippetAuditAspect.class);

    @Before("execution(* com.ruoyi.system.service..*.*(..))")
    public void auditServiceCall(JoinPoint point)
    {
        if (log.isDebugEnabled())
        {
            log.debug("knowledge-audit stub: {}", point.getSignature().toShortString());
        }
    }
}
