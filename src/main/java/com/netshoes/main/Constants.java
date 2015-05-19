package com.netshoes.main;

import org.hornetq.core.remoting.impl.netty.TransportConstants;

public class Constants {

	public static final int PORT = 5445;
	public static final String HOST = "172.18.0.172";
	
	public static final String QUEUE = "javax.jmx.Queue";
	public static final String DESTINATION_TYPE = "destinationType";
	
	public static final String CONNECTIONS_PARAM = "connectionParameters";
	public static final String CONNECTIONS_PARAM_VALUE = TransportConstants.HOST_PROP_NAME + "=" + HOST + ";" + TransportConstants.PORT_PROP_NAME + "=" + PORT;
	
	public static final String CONNECTOR_CLASS_NAME = "connectorClassName";
	public static final String CONNECTOR_CLASS_NAME_VALUE = "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory";
	
	public static final String DESTINATION = "destination";
	public static final String DESTINATION_VALUE = "CatalogQueue";
	
}
