package com.kapx.jboss.jee6.security.client;

import java.io.File;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapx.jboss.jee6.security.ejb.HelloWorld;

public class HelloWorldRemoteClient {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldRemoteClient.class);

	public static void main(String[] args) {
		try {
			HelloWorld helloWorldEJB = getHelloWorldRemote();
			String output = helloWorldEJB.sayHello("DeKapx");
			logger.info("##################### Output from EJB {} #####################", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static HelloWorld getHelloWorldRemote() throws Exception {
		final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

		Properties props = new Properties();
		props.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
		props.put("jboss.naming.client.ejb.context", true);

		props.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		props.put("java.naming.provider.url", "remote://127.0.0.1:4447");
		props.put("jboss.naming.client.ejb.context", "true");
		props.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");

		props.put("jboss.naming.client.remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "true");
		props.put("jboss.naming.client.connect.options.org.xnio.Options.SSL_STARTTLS", "true");

		defineSystemProperties();

		Context context = new InitialContext(props);

		final HelloWorld remote = (HelloWorld) context.lookup(getJndiName());
		context.close();
		return remote;
	}

	private static String getJndiName() {
		final String appName = "ejb-x509-security";
		final String beanName = "HelloWorldBean";
		return appName + "/" + beanName + "!" + HelloWorld.class.getName();
	}

	public static void defineSystemProperties() {
		File clientKeyStore = new File("src/main/resources/client-keystore.jks");
		System.setProperty("javax.net.ssl.keyStore", clientKeyStore.getAbsolutePath());
		System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

		File clientTrustStore = new File("src/main/resources/client-truststore.jks");
		System.setProperty("javax.net.ssl.trustStore", clientTrustStore.getAbsolutePath());
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	}
}
