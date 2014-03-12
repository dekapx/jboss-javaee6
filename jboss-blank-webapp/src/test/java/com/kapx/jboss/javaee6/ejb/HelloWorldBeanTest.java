package com.kapx.jboss.javaee6.ejb;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.kapx.jboss.javaee6.interceptor.LoggingInterceptor;

@RunWith(Arquillian.class)
public class HelloWorldBeanTest {
	@Deployment(name = "jboss-javaee6-war")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "jboss-javaee6-webapp.war");
		war.addClass(HelloWorldLocal.class);
		war.addClass(HelloWorldRemote.class);
		war.addClass(HelloWorldBean.class);
		war.addClass(LoggingInterceptor.class);
		war.addAsWebInfResource("META-INF/beans.xml");
		// war.addAsWebInfResource("jboss-ejb-client.properties");
		return war;
	}

	@EJB(mappedName = "jboss-blank-webapp/HelloWorldBean!com.kapx.jboss.javaee6.ejb.HelloWorldRemote")
	private HelloWorldRemote helloWorldEJB;

	@Test
	@InSequence(1)
	public void test_sayHello() {
		// HelloWorldRemote helloWorldEJB = getHelloWorldRemote();
		String actual = helloWorldEJB.sayHello("Kapx");
		System.out.println("Response: " + actual);
		assertEquals("Hello !!! Kapx", actual);
	}

	private static HelloWorldRemote getHelloWorldRemote() {
		HelloWorldRemote remote = null;
		try {
			final Properties ejbProperties = new Properties();
			ejbProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			ejbProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
			ejbProperties.put(Context.SECURITY_PRINCIPAL, "admin");
			ejbProperties.put(Context.SECURITY_CREDENTIALS, "p@ssw0rd");
			ejbProperties.put("jboss.naming.client.ejb.context", true);
			final Context context = new InitialContext(ejbProperties);
			remote = (HelloWorldRemote) context.lookup(getJndiName());
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return remote;
	}

	private static String getJndiName() {
		final String appName = "jboss-javaee6-webapp";
		final String beanName = "HelloWorldBean";
		return appName + "/" + beanName + "!" + HelloWorldRemote.class.getName();
	}
}
