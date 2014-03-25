package com.kapx.jboss.jee6.jobs.listener;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapx.jboss.jee6.model.TaskRequest;

@Singleton
public class TaskRequestListener {
	private static final Logger logger = LoggerFactory.getLogger(TaskRequestListener.class);

	public void onEvent(@Observes final TaskRequest taskRequest) {
		logger.info("Event received! processing event of type {} ", taskRequest);
	}
}
