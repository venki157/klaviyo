package com.klaviyo.demo.notificationservice.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.klaviyo.demo.notificationservice.email.EmailServiceImpl;

@Component
public class RegistrationCreateEventReceiver {
	
	@Autowired
	private EmailServiceImpl emailSender;

	@RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedMessage(Object msg) {
        System.out.println("Recieved Message: ");
        emailSender.send(msg);
    }
}
