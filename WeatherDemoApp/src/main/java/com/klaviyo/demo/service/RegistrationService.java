package com.klaviyo.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klaviyo.demo.entity.RegistrationDAO;
import com.klaviyo.demo.entity.vo.RegistrationEntity;
import com.klaviyo.demo.rest.services.response.RegistrationResponse;

/**
 * RegistrationService provides business layer logic to handle user registration.
 * It performs 1) calls the Registration DAO to save user information in Database.
 *             2) Places the user information in RabbitMQ queue.
 * @TODO Exception handling.
 * 
 * @author gangadarkatakam
 *
 */
@Service
public class RegistrationService {
	
	@Autowired
	RegistrationDAO registrationRepo;
	
	@Autowired
	RegistrationPublisher publisher;

	/**
	 * Saves the new User RegistrationDetails in db & places user details on RabbitMQ queue.
	 * @param entity
	 * @throws JsonProcessingException 
	 */
	public void saveEntity(RegistrationEntity entity) {
		registrationRepo.save(entity);
		// After successful registration send an event to RabbitMQ to process newsletter to the registered user.
		Message message;
		try {
			ObjectMapper mapper = new ObjectMapper();
			message = MessageBuilder.withBody(mapper.writeValueAsBytes(entity)).build();
			publisher.produceMsg(message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Returns all Registered users from database.
	 * @return
	 */
	public List<RegistrationResponse> getAllRegisteredUsers() {
		Iterable<RegistrationEntity> registeredUsers =  registrationRepo.findAll();
		List<RegistrationResponse> registrationResponseList = new ArrayList<RegistrationResponse>();
		registeredUsers.forEach(e -> {registrationResponseList.add(new RegistrationResponse(e)); });
		return registrationResponseList;
	}
	

}
