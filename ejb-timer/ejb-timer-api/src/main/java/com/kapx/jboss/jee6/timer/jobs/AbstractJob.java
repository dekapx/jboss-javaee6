package com.kapx.jboss.jee6.timer.jobs;

@SuppressWarnings("serial")
public abstract class AbstractJob implements Job {
	protected String jobId;
	protected String jobDesc;

	protected AbstractJob(final String jobId, final String jobDesc) {
		this.jobId = jobId;
		this.jobDesc = jobDesc;
	}

	public String getJobId() {
		return jobId;
	}

	public String getJobDesc() {
		return jobDesc;
	}

}
