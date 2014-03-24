package com.kapx.jboss.jee6.timer.api;

import java.io.Serializable;

import javax.ejb.ScheduleExpression;
import javax.ejb.Timer;

import com.kapx.jboss.jee6.jobs.api.TimerJob;

public interface SchedulerService {
	Timer createTimer(TimerJob<? extends Serializable> timerJob, ScheduleExpression expression, boolean persistant);
}
