package com.kapx.jboss.jee6.security.ejb;

import static org.junit.Assert.assertNotNull;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
public class HelloWorldTest {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);

	@Deployment(name = "ejb-security-test-webap")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "ejb-security-test-webap.war");

		war.addClass(HelloWorld.class);
		war.addClass(HelloWorldBean.class);

		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		return war;
	}

	@EJB
	private HelloWorld helloWorld;

	@Test
	public void testSayHello() throws Exception {
		final String output = helloWorld.sayHello("Test");
		logger.info("Calling HelloWorld EJB from Arquillian...");
		assertNotNull(output);
	}
}
