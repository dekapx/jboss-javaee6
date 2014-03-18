package com.kapx.jboss.jee6.timer.jobs;

import java.io.Serializable;

public interface Job extends Serializable {
	void execute();
}
