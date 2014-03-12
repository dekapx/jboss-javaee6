package com.ericsson.jboss.javaee6.ejb;

import java.io.Serializable;
import java.util.Date;

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

import com.ericsson.jboss.javaee6.jobs.Job;

@Stateless
@Local(JobScheduler.class)
public class JobSchedulerImpl implements JobScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(JobSchedulerImpl.class.getName());

	@Resource
	private TimerService timerService;

	@Timeout
	public void timeout(final Timer timer) {
		LOG.info("Executing job for {} at {}", timer.getInfo(), new Date());
		Job job = (Job) timer.getInfo();
		scheduleJob(job);
	}

	@Override
	public Timer startTimer(final ScheduleExpression expression, final boolean persistant, final Serializable info) {
		if (expression == null) {
			throw new IllegalArgumentException("Schedule Expression must not be null.");
		}

		final TimerConfig timerConfig = new TimerConfig(info, persistant);
		LOG.info("new job is schedules for {} ", info.getClass().getName());
		return timerService.createCalendarTimer(expression, timerConfig);
	}

	@Override
	public void stopTimer(final Timer timer) {
		if (timer == null) {
			throw new IllegalArgumentException("Timer must not be null.");
		}

		timer.cancel();
	}

	private void scheduleJob(final Job job) {
		LOG.info("scheduling job of type {}", job.getClass().getName());
		job.execute();
	}

}
