package com.klaviyo.demo.rest.services.response;

import com.klaviyo.demo.entity.vo.RegistrationEntity;

/**
 * RegistrationResponse class captures the user details and send as part of REST GET calls.
 * 
 * @author gangadarkatakam
 *
 */
public class RegistrationResponse {
	
	private String email;
	
	private String country;
	
	public RegistrationResponse(RegistrationEntity entity) {
		this.email = entity.getEmail();
		this.country = entity.getCity();
	}

	public RegistrationResponse(String email, String country) {
		super();
		this.email = email;
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public String getCountry() {
		return country;
	}

}
