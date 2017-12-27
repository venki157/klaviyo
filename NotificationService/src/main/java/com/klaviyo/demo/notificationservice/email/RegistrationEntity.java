package com.klaviyo.demo.notificationservice.email;

/**
 * RegistrationEntity item will be received from the queue item.
 * 
 * @author gangadarkatakam
 *
 */
public class RegistrationEntity {
	
    private Integer id;
	
	private String email;
	
	private String city;
	
	private String state;
	
	public RegistrationEntity() {}
	
	public RegistrationEntity(final String email, final String state, final String city) {
		this.email = email;
		this.city = city;
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public String getCity() {
		return city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return "RegistrationEntity [id=" + id + ", email=" + email + ", city=" + city + ", state=" + state + "]";
	}

}
