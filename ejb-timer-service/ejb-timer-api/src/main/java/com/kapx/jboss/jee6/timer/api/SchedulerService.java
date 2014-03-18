package com.kapx.jboss.jee6.timer.api;

import javax.ejb.ScheduleExpression;
import javax.ejb.Timer;

import com.kapx.jboss.jee6.timer.jobs.AbstractJob;

public interface SchedulerService {
	Timer createTimer(ScheduleExpression expression, boolean persistant, AbstractJob job);
}
