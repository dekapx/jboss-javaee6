package com.kapx.jboss.jee6.jobs.impl;

import com.kapx.jboss.jee6.jobs.api.TimerJob;
import com.kapx.jboss.jee6.model.FileSearchTaskRequest;

@SuppressWarnings("serial")
public class TimerJobImpl implements TimerJob<FileSearchTaskRequest> {
	private String jobId;
	private String jobDesc;
	private FileSearchTaskRequest taskRequest;

	public TimerJobImpl(final String jobId, final String jobDesc, final FileSearchTaskRequest taskRequest) {
		this.jobId = jobId;
		this.jobDesc = jobDesc;
		this.taskRequest = taskRequest;
	}

	public String getJobId() {
		return jobId;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public FileSearchTaskRequest getTaskRequest() {
		return taskRequest;
	}
}
