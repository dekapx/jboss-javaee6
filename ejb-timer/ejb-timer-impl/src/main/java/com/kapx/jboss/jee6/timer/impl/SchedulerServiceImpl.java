package com.kapx.jboss.jee6.timer.impl;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapx.jboss.jee6.jobs.api.TimerJob;
import com.kapx.jboss.jee6.timer.api.SchedulerService;

@Stateless
@Local(SchedulerService.class)
public class SchedulerServiceImpl implements SchedulerService {
	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Resource
	private TimerService timerService;

	@Override
	public Timer createTimer(final TimerJob<? extends Serializable> timerJob, final ScheduleExpression expression, boolean persistant) {
		if (timerJob == null) {
			throw new IllegalArgumentException("Timer job must not be null.");
		}

		if (expression == null) {
			throw new IllegalArgumentException("Schedule Expression must not be null.");
		}

		final TimerConfig timerConfig = new TimerConfig(timerJob, persistant);
		logger.info("new job is schedules for jobId {} ", timerJob.getJobId());
		return timerService.createCalendarTimer(expression, timerConfig);
	}

	@Timeout
	public void timeout(final Timer timer) {
		TimerJob timerJob = (TimerJob) timer.getInfo();
		logger.info("Executing timer job for jobId {}", timerJob.getJobId());
	}

}
