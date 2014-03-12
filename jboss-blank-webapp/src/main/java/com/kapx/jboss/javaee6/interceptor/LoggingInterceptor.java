package com.kapx.jboss.javaee6.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        LOG.info("LoggingInterceptor - before EJB method invoke: " + ctx.getMethod().getName());

        Object result = ctx.proceed();
        LOG.debug("Result [ {} ] from EJB invocation.", result);

        LOG.info("LoggingInterceptor - after EJB method invoke: " + ctx.getMethod().getName());
        return result;
    }
}
