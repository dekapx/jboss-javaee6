package com.kapx.jboss.javaee6.ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class HelloWorldRemoteClient {
	public static void main(String[] args) {
		try {
			HelloWorldRemote remote = getHelloWorldRemote();
			System.out.println(remote.sayHello("DeKapx"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static HelloWorldRemote getHelloWorldRemote() throws Exception {
		final Properties ejbProperties = new Properties();
		ejbProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		ejbProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
		ejbProperties.put(Context.SECURITY_PRINCIPAL, "admin");
		ejbProperties.put(Context.SECURITY_CREDENTIALS, "p@ssw0rd");
		ejbProperties.put("jboss.naming.client.ejb.context", true);
		final Context context = new InitialContext(ejbProperties);

		final HelloWorldRemote remote = (HelloWorldRemote) context.lookup(getJndiName());
		context.close();
		return remote;
	}

	private static String getJndiName() {
		final String appName = "jboss-blank-webapp";
		final String beanName = "HelloWorldBean";
		return appName + "/" + beanName + "!" + HelloWorldRemote.class.getName();
	}
}
