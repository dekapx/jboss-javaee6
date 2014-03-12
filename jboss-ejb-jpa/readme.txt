+------------------------------------------------------------+
	Datasource configuration for JBoss standalone-full.xml
+------------------------------------------------------------+
- Create a JBoss module for mysql in JBOSS_HOME\module folder.

--------------------------------------------------------------------------------------------------------------------
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
	<resources>
		<resource-root path="mysql-connector-java-5.1.27-bin.jar" />
	</resources>
	<dependencies>
		<module name="javax.api" />
		<module name="javax.transaction.api" />
		<module name="javax.servlet.api" optional="true" />
	</dependencies>
</module>

--------------------------------------------------------------------------------------------------------------------	
<datasource jndi-name="java:jboss/datasources/MySQLDS" pool-name="MySQLDS" enabled="true" use-java-context="true">
<connection-url>jdbc:mysql://localhost:3306/test</connection-url>
<driver>mysql</driver>
    <security>
        <user-name>root</user-name>
        <password>root</password>
    </security>
</datasource>

<driver name="mysql" module="com.mysql">
    <xa-datasource-class>com.mysql.jdbc.Driver</xa-datasource-class>
</driver>
--------------------------------------------------------------------------------------------------------------------
mvn clean test -Parq-jbossas-remote
--------------------------------------------------------------------------------------------------------------------