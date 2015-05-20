package com.netshoes.web;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;

import com.netshoes.main.Constants;

@MessageDriven(name = "CatalogQueueWithHornetQ", activationConfig = {
		@ActivationConfigProperty(propertyName = Constants.DESTINATION_TYPE, propertyValue = Constants.QUEUE),
		@ActivationConfigProperty(propertyName = Constants.DESTINATION, propertyValue = Constants.DESTINATION_VALUE) })
@ResourceAdapter(Constants.CONNECTION_FACTORY_SIMPLE_NAME)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class HornetQ implements MessageListener {

	public void onMessage(Message message) {
		try {
			System.out.println(message.getBody(String.class).toString());
		} catch (JMSException e) {
			System.out.println(message);
		}
	}

}
