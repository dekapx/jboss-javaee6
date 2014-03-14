package com.kapx.jboss.jee6.timer.api;

import java.io.Serializable;

import javax.ejb.ScheduleExpression;
import javax.ejb.Timer;

public interface SchedulerService {
	Timer createTimer(ScheduleExpression expression, boolean persistant, Serializable serializable);
}
