package com.kapx.jboss.jee6.timer.impl;

import java.util.Collection;

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
import com.kapx.jboss.jee6.timer.jobs.AbstractJob;

@Stateless
@Local(SchedulerService.class)
public class SchedulerServiceImpl implements SchedulerService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Resource
	private TimerService timerService;

	public void timeout(final Timer timer) {
		AbstractJob job = (AbstractJob) timer.getInfo();
		logger.info("Executing timer job for jobId {}", job.getJobId());

		processJob(job);
	}

	@Override
	public Timer createTimer(ScheduleExpression expression, boolean persistant, AbstractJob job) {
		if (expression == null) {
			throw new IllegalArgumentException("Schedule Expression must not be null.");
		}

		final TimerConfig timerConfig = new TimerConfig(job, persistant);
		logger.info("new job is schedules for jobId {} ", job.getJobId());
		return timerService.createCalendarTimer(expression, timerConfig);
	}

	public void cancelTimer(final String jobId) {
		if (jobId == null || jobId.isEmpty()) {
			throw new IllegalArgumentException("Job id must not be null.");
		}

		final Collection<Timer> timers = timerService.getTimers();
		for (Timer timer : timers) {
			AbstractJob abstractJob = (AbstractJob) timer.getInfo();
			if (abstractJob.getJobId().equals(jobId)) {
				timer.cancel();
				break;
			}
		}
	}

	private void processJob(final AbstractJob job) {
		logger.info("project job for jobId {}", job.getJobId());
		job.execute();
	}
}
