package com.klaviyo.demo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class is responsible to place the new user details onto the 
 * queue and the user will be intimated with the weather based coupons.
 * 
 * @author gangadarkatakam
 *
 */
@Component
public class RegistrationPublisher {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;

	/**
	 * Produce the given Object message to the consumer.
	 * @param msg
	 */
	public void produceMsg(Object msg){
		amqpTemplate.convertAndSend(exchange, "", msg);
		System.out.println("Send msg = " + msg);
	}
}
