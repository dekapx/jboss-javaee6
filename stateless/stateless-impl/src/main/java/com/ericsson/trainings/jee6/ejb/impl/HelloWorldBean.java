package com.ericsson.trainings.jee6.ejb.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
		LOGGER.info("sayHello() method invoked with parameter \"{}\"", arg);
		return "Hello !!! " + arg;
	}

}
