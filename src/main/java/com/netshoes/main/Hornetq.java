package com.netshoes.main;

import java.util.Map;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.ClientConsumer;
import org.hornetq.api.core.client.ClientMessage;
import org.hornetq.api.core.client.ClientProducer;
import org.hornetq.api.core.client.ClientSession;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.api.core.client.ServerLocator;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;

public class Hornetq {

	public static void main(String[] args) throws Exception {
		 TransportConfiguration transportConfiguration = new
		 TransportConfiguration(NettyConnectorFactory.class.getName());
		
		 Map<String, Object> params = transportConfiguration.getParams();
		 params.put(TransportConstants.HOST_PROP_NAME, Constants.HOST);
		 params.put(TransportConstants.PORT_PROP_NAME, Constants.PORT);
		
		 ServerLocator locator = HornetQClient.createServerLocatorWithoutHA(transportConfiguration);
		 ClientSessionFactory factory = locator.createSessionFactory();
		
		 ClientSession session = factory.createSession();
		
		 try {
			 session.createQueue(Constants.DESTINATION_VALUE, Constants.DESTINATION_VALUE, true);
		 } catch (Exception e) {
		 }
		 
		 ClientProducer producer = session.createProducer(Constants.DESTINATION_VALUE);
		
		 ClientMessage message = session.createMessage(true);
		 message.getBodyBuffer().writeString("Hello");
		
		 producer.send(message);
		
		 session.start();
		
		 ClientConsumer consumer = session.createConsumer(Constants.DESTINATION_VALUE);
		
		 ClientMessage msgReceived = consumer.receive();
		
		 System.out.println("message = " + msgReceived.getBodyBuffer().readString());
		 
		 msgReceived.acknowledge();
		 
		 session.close();
	}

}
