package com.kapx.jboss.jee6.security.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.security.annotation.SecurityDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Remote(HelloWorld.class)
@SecurityDomain("x509-security-domain")
public class HelloWorldBean implements HelloWorld {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldBean.class);

	@Override
	public String sayHello(String arg) {
		if (arg == null || arg.isEmpty()) {
			throw new IllegalArgumentException("Argument should not be null...");
		}
		logger.info("HelloWorldBean bean invoked with {} parameter", arg);
		return "Hello !!! " + arg;
	}

}
