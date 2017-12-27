package com.klaviyo.demo.entity.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class is a bean used in communicating with backend.
 * 
 * @author gangadarkatakam
 *
 */
@Entity
@Table(name = "User")
public class RegistrationEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(nullable = false, unique = true)
	private final String email;
	
	@Column(nullable = false)
	private final String city;
	
	@Column(nullable = false)
	private final String state;
	
	public RegistrationEntity(final String email, final String state, final String city) {
		this.email = email;
		this.state = state;
		this.city = city;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationEntity other = (RegistrationEntity) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegistrationEntity [id=" + id + ", email=" + email + ", city=" + city + ", state=" + state + "]";
	}

}
