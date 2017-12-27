package com.klaviyo.demo.rest.services.request;

/**
 * RegistrationRequest is bean captures the user request details while creating a new user.
 * 
 * @author gangadarkatakam
 *
 */
public class RegistrationRequest {
	
	private String email;
	
	private String city;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
