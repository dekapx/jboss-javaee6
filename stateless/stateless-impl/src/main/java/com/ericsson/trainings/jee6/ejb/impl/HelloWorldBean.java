package com.ericsson.trainings.jee6.ejb.impl;

import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.ejb.api.HelloWorldLocal;
import com.ericsson.trainings.jee6.ejb.api.HelloWorldRemote;

@Stateless
@Local(HelloWorldLocal.class)
@Remote(HelloWorldRemote.class)
public class HelloWorldBean implements HelloWorldLocal, HelloWorldRemote {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldBean.class);

	@PostConstruct
	public void setup() {
		LOGGER.info("HelloWorldBean initialized...");
	}

	@PreDestroy
	public void teardown() {
		LOGGER.info("HelloWorldBean destroyed...");
	}

	@Override
	public String sayHello(final String arg) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("sayHello() method invoked with parameter \"{}\"", arg);
		return "Hello !!! " + arg;
	}

	@Override
	@Asynchronous
	public Future<String> sayHelloAsync(final String arg) {
		LOGGER.info("sayHelloAsync() method invoked with parameter \"{}\"", arg);
		return new AsyncResult<String>("Hello !!! " + arg);
	}

}
