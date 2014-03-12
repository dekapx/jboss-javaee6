package com.kapx.jboss.javaee6.ejb.jse.client;

import java.util.Properties;

import javax.naming.*;

import com.kapx.jboss.javaee6.ejb.HelloWorldRemote;

public class HelloWorldBeanJSEClient {
    public static void main(String[] args) {
        try {
            HelloWorldRemote remote = performHelloWorldRemoteLookup();
            System.out.println(remote.sayHello("Kapx"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HelloWorldRemote performHelloWorldRemoteLookup() throws NamingException {
        final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);

        jndiProperties.put("remote.connections", "default");
        jndiProperties.put("remote.connection.default.port", "4447");
        jndiProperties.put("remote.connection.default.host", "localhost");

        Context context = new InitialContext(jndiProperties);
        HelloWorldRemote remote = (HelloWorldRemote) context.lookup(getJndiName());
        return remote;
    }

    private static String getJndiName() {
        final String appName = "jboss-javaee6-webapp";
        final String beanName = "HelloWorldBean";
        String jndiName = "ejb:" + appName + "/" + beanName + "!com.kapx.jboss.javaee6.ejb.HelloWorldRemote";
        return jndiName;
    }
}
