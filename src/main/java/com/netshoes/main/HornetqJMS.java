package com.netshoes.main;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.core.remoting.impl.netty.TransportConstants;

public class HornetqJMS {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		try {
			// Step 1. Directly instantiate the JMS Queue object.
			Queue queue = HornetQFactory.createQueue(Constants.DESTINATION_SIMPLE_NAME);

			// Step 2. Instantiate the TransportConfiguration object which
			// contains the knowledge of what transport to use,
			// The server port etc.

			Map<String, Object> connectionParams = new HashMap<String, Object>();
			connectionParams.put(TransportConstants.HOST_PROP_NAME, Constants.HOST);
			connectionParams.put(TransportConstants.PORT_PROP_NAME, Constants.PORT);

			ConnectionFactory cf = HornetQFactory.createConnectionFactory(connectionParams);

			// Step 4.Create a JMS Connection
			connection = cf.createConnection();

			// Step 5. Create a JMS Session
			Session session = connection.createSession();

			// Step 6. Create a JMS Message Producer
			MessageProducer producer = session.createProducer(queue);

			// Step 7. Create a Text Message
			TextMessage message = session.createTextMessage("This is a text message");

			System.out.println("Sent message: " + message.getText());

			// Step 8. Send the Message
			producer.send(message);

			// Step 9. Create a JMS Message Consumer
			MessageConsumer messageConsumer = session.createConsumer(queue);

			// Step 10. Start the Connection
			connection.start();

			// Step 11. Receive the message
			TextMessage messageReceived = (TextMessage) messageConsumer.receive();

			System.out.println("Received message: " + messageReceived.getText());
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

}
