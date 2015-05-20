package com.netshoes.web;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.netshoes.main.Constants;

@Path("send")
public class HornetQService {
	
	@Resource(mappedName = Constants.CONNECTION_FACTORY_VALUE)
	private ConnectionFactory cf;
	
	@Resource(mappedName = Constants.DESTINATION_VALUE)
	private Queue queue;
	
	@POST
	public void send(String text) throws JMSException{
		Connection connection = null;
		try {
			connection = this.cf.createConnection();
			Session session = connection.createSession();
			MessageProducer producer = session.createProducer(this.queue);
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
