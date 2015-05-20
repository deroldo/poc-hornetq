package com.netshoes.main;

import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;

public class HornetQFactory {

	public static Queue createQueue(String destination) {
		return HornetQJMSClient.createQueue(destination);
	}

	public static ConnectionFactory createConnectionFactory(Map<String, Object> connectionParams) {
		TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), connectionParams);
		ConnectionFactory cf = HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF, transportConfiguration);
		return cf;
	}

}
