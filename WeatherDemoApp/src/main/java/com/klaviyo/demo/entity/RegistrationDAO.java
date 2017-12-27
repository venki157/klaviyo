package com.klaviyo.demo.entity;

import org.springframework.data.repository.CrudRepository;

import com.klaviyo.demo.entity.vo.RegistrationEntity;

/**
 * DAO responsible for CRUD operations.
 * 
 * @author gangadarkatakam
 *
 */
public interface RegistrationDAO extends CrudRepository<RegistrationEntity, Long> {
	
}
