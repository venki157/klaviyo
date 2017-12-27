package com.klaviyo.demo.rest.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.klaviyo.demo.entity.vo.RegistrationEntity;
import com.klaviyo.demo.rest.services.request.RegistrationRequest;
import com.klaviyo.demo.rest.services.response.RegistrationResponse;
import com.klaviyo.demo.rest.services.util.EmailValidator;
import com.klaviyo.demo.service.RegistrationService;

/**
 * REST service responsible for user registration HTTP methods such as POST, GET.
 * 
 * @author gangadarkatakam
 *
 */
@RestController
@RequestMapping(value="/weather")
public class RegistrationRestService {
	
	@Autowired
	RegistrationService registrationService;
	
	private static Map<String, String> cityMap = new HashMap<String, String>();
	
	//@TODO Metadata shouldn't be hard-coded. We should actually get it from DB.
	static {
		cityMap.put("Anchorage", "AK");
		cityMap.put("Austin", "TX");
		cityMap.put("Boston", "MA");
		cityMap.put("Seattle", "WA");
		cityMap.put("Washington", "DC");
	}
	
	/**
	 * POST request to register a new user to weather app.
	 * @param newRegistration - New user information.
	 * @return response - returns the registration successful message.
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ResponseEntity registerUser(@RequestBody RegistrationRequest newRegistration) {
		StringBuilder responseStr = null;
		// Validate the request here.
		if (null != newRegistration) {
			if (!EmailValidator.isValidEmail(newRegistration.getEmail())) {
				return ResponseEntity.badRequest().build();
			}
		}
		RegistrationEntity entity = new RegistrationEntity(newRegistration.getEmail(), cityMap.get(newRegistration.getCity()), newRegistration.getCity());
		
		try {
			registrationService.saveEntity(entity);
			responseStr = new StringBuilder(newRegistration.getEmail());
			responseStr.append(" is registered succssfully.");
		} catch (Exception e) {
			if (e.getMessage().contains("Duplicate entry")) {
				return ResponseEntity.badRequest().body("Email already registered");
			}
			return ResponseEntity.badRequest().body("Server is not able to process request.");
		}
		return ResponseEntity.ok(responseStr.toString());
	}

	/**
	 * GET calls return all the registered users.
	 * @return
	 */
	@RequestMapping(value="/register/all", method = RequestMethod.GET)
	public ResponseEntity<RegistrationResponse> getRegisteredUsers() {
		List<RegistrationResponse> allRegistrations = registrationService.getAllRegisteredUsers();
		System.out.println(allRegistrations);
		return new ResponseEntity(allRegistrations, HttpStatus.OK);
	}

	
}
