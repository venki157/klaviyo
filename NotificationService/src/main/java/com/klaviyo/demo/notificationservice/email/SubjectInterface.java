package com.klaviyo.demo.notificationservice.email;

import org.springframework.stereotype.Component;

/**
 * Interface to generate email subject based on template.
 * @author gangadarkatakam
 *
 */
@Component
public interface SubjectInterface {
	
	/**
	 * Return the email subject related to a template.
	 * @return
	 */
	public String generateSubject();

}
