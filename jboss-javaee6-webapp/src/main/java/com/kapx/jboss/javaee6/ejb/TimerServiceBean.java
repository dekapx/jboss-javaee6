package com.kapx.jboss.javaee6.ejb;

import javax.ejb.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class TimerServiceBean {
    private static final Logger LOG = LoggerFactory.getLogger(TimerServiceBean.class.getName());

    @EJB
    private HelloWorldLocal helloWorldEJB;

    @Schedules({ @Schedule(hour = "*", minute = "*", second = "10") })
    public void startTimer() {
        LOG.info("Calling helloWorldEJB from startTimer...");
        helloWorldEJB.sayHello("Kapx from timer");
    }

}
