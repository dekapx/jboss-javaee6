package com.ericsson.jboss.javaee6.ejb;

import java.io.Serializable;

import javax.ejb.ScheduleExpression;
import javax.ejb.Timer;

public interface JobScheduler {
	Timer startTimer(ScheduleExpression expression, boolean persistant, Serializable info);

	void stopTimer(Timer timer);
}
