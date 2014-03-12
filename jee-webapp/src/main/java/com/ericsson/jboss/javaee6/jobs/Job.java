package com.ericsson.jboss.javaee6.jobs;

import java.io.Serializable;

public interface Job extends Serializable {

	void execute();

}
