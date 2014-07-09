package com.ericsson.trainings.jee6.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.Future;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.ejb.api.HelloWorldLocal;
import com.ericsson.trainings.jee6.ejb.api.HelloWorldRemote;
import com.ericsson.trainings.jee6.ejb.impl.HelloWorldBean;

@RunWith(Arquillian.class)
public class HelloWorldTest {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);

	@Deployment(name = "test-stateless-ejb")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "test-stateless-ejb.war");

		war.addClass(HelloWorldLocal.class);
		war.addClass(HelloWorldRemote.class);
		war.addClass(HelloWorldBean.class);

		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		return war;
	}

	@EJB
	private HelloWorldLocal helloWorldEJB;

	@Test
	@InSequence(1)
	public void testSayHello() throws Exception {
		logger.info("Calling HelloWorld EJB from Arquillian...");

		final String expected = "Hello !!! Test";
		final String output = helloWorldEJB.sayHello("Test");

		assertNotNull(output);
		assertEquals(expected, output);
	}

	@Test
	@InSequence(2)
	public void testSayHelloAsync() throws Exception {
		logger.info("Calling HelloWorld EJB from Arquillian...");

		final String expected = "Hello !!! Test";
		final Future<String> future = helloWorldEJB.sayHelloAsync("Test");

		assertNotNull(future);
		assertEquals(expected, future.get());
	}
}
