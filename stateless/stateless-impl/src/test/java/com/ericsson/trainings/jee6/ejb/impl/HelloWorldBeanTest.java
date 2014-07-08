package com.ericsson.trainings.jee6.ejb.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloWorldBeanTest {
	private HelloWorldBean helloWorldBean;

	@Before
	public void setUp() throws Exception {
		helloWorldBean = new HelloWorldBean();
	}

	@After
	public void tearDown() throws Exception {
		helloWorldBean = null;
	}

	@Test
	public void testSayHello() {
		final String expected = "Hello !!! Test";
		final String output = helloWorldBean.sayHello("Test");

		assertNotNull(output);
		assertEquals(expected, output);
	}

}
