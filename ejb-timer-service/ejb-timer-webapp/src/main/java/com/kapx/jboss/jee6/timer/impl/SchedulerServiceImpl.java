package com.kapx.jboss.jee6.timer.impl;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapx.jboss.jee6.timer.api.SchedulerService;

@Stateless
@Local(SchedulerService.class)
public class SchedulerServiceImpl implements SchedulerService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Resource
	private TimerService timerService;

	public void timeout(final Timer timer) {
		logger.info("Executing timer job for {}", timer.getInfo());
		timer.getInfo();
	}

	@Override
	public Timer createTimer(ScheduleExpression expression, boolean persistant, Serializable serializable) {
		if (expression == null) {
			throw new IllegalArgumentException("Schedule Expression must not be null.");
		}

		final TimerConfig timerConfig = new TimerConfig(serializable, persistant);
		logger.info("new job is schedules for {} ", serializable.getClass().getName());
		return timerService.createCalendarTimer(expression, timerConfig);
	}

}
