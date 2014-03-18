package com.kapx.jboss.jee6.timer.api;

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

import com.kapx.jboss.jee6.timer.impl.SchedulerServiceImpl;

@RunWith(Arquillian.class)
public class SchedulerServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceTest.class);

	@Deployment(name = "ejb-security-test-webap")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "ejb-security-test-webap.war");

		war.addClass(SchedulerService.class);
		war.addClass(SchedulerServiceImpl.class);

		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		return war;
	}

	@EJB
	private SchedulerService schedulerService;

	@Test
	public void testCreateTimer() throws Exception {
	}

}
