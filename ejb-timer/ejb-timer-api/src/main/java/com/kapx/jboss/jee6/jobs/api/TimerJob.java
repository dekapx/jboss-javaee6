package com.kapx.jboss.jee6.jobs.api;

import java.io.Serializable;

public interface TimerJob<T> extends Serializable {
	String getJobId();

	String getJobDesc();

	T getTaskRequest();
}
