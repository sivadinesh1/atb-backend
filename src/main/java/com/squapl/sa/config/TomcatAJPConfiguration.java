package com.squapl.sa.config;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.valves.RemoteIpValve;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TomcatAJPConfiguration {

//@Bean
//@SuppressWarnings("static-method")
public EmbeddedServletContainerFactory servletContainer() {
	 TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
	    if (true)
	    {
	        Connector ajpConnector = new Connector("AJP/1.3");
	        ajpConnector.setProtocol("AJP/1.3");
	        ajpConnector.setPort(8009);
	        ajpConnector.setSecure(false);
	        ajpConnector.setAllowTrace(false);
	        ajpConnector.setScheme("http");
	        tomcat.addAdditionalTomcatConnectors(ajpConnector);
	    }

	    return tomcat;
}



}