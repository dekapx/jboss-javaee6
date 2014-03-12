package com.kapx.jboss.javaee6.ejb;

import javax.ejb.*;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapx.jboss.javaee6.interceptor.LoggingInterceptor;

@Stateless
@Local(HelloWorldLocal.class)
@Remote(HelloWorldRemote.class)
@Interceptors({ LoggingInterceptor.class })
public class HelloWorldBean implements HelloWorldLocal, HelloWorldRemote {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldBean.class.getName());

    @Override
    public String sayHello(final String arg) {
        LOG.info("sayHello invoked with [ {} ] argument", arg);

        if (arg == null || arg.isEmpty()) {
            throw new IllegalArgumentException("Argument must not be null.");
        }

        return "Hello !!! " + arg;
    }

}
