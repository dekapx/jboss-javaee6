package com.ericsson.jboss.javaee6.jobs;

@SuppressWarnings("serial")
public class FileCollectionJob implements Job {

	@Override
	public void execute() {
		System.out.println("...................... Executing File Collection Job ...................... ");
	}

}
