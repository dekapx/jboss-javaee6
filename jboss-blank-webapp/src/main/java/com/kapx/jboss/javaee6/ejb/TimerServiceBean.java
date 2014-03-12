package com.kapx.jboss.javaee6.ejb;

import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class TimerServiceBean {
	private static final Logger LOG = LoggerFactory.getLogger(TimerServiceBean.class.getName());

	@Schedules({ @Schedule(hour = "*", minute = "*", second = "5") })
	public void startTimer() {
		LOG.info("@Schedule startTimer...");
	}

}
