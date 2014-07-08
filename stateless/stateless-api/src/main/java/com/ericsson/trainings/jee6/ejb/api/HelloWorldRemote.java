package com.ericsson.trainings.jee6.ejb.api;

import java.util.concurrent.Future;

public interface HelloWorldRemote {
	String sayHello(String arg);

	Future<String> sayHelloAsync(String arg);
}
