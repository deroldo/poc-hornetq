package com.netshoes.web;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.hornetq.core.remoting.impl.netty.TransportConstants;

import com.netshoes.main.Constants;
import com.netshoes.main.HornetQFactory;

@Path("send")
public class HornetQService {
	
	@POST
	public void send(String text) throws JMSException{
		Connection connection = null;
		try {
			Map<String, Object> connectionParams = new HashMap<String, Object>();
			connectionParams.put(TransportConstants.HOST_PROP_NAME, Constants.HOST);
			connectionParams.put(TransportConstants.PORT_PROP_NAME, Constants.PORT);
			ConnectionFactory cf = HornetQFactory.createConnectionFactory(connectionParams);
			connection = cf.createConnection();
			Session session = connection.createSession();
			Destination queue = HornetQFactory.createQueue(Constants.DESTINATION_VALUE);
			MessageProducer producer = session.createProducer(queue );
			TextMessage message = session.createTextMessage(text);
			producer.send(message);
			connection.start();
		} finally {
			if (connection != null){
				connection.close();
			}
		}
	}

}
