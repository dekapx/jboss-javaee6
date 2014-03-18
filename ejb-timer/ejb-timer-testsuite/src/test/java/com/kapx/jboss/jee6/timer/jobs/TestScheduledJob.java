package com.kapx.jboss.jee6.timer.jobs;

@SuppressWarnings("serial")
public class TestScheduledJob<T> extends AbstractJob {

	public TestScheduledJob(final String jobId, final String jobDesc) {
		super(jobId, jobDesc);
	}

	@Override
	public void execute() {
		System.out.println("Executing file collection job...");
	}

}
