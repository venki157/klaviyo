package com.klaviyo.demo.notificationservice.email;

import org.springframework.context.annotation.ComponentScan;

/**
 * Preprocessor performs activities like generating the body of the email
 * using templates using freemaker, velocity...etc.
 * It can also talk to any other webservice to get the information.
 * For example, real weather service updates.
 * @author gangadarkatakam
 *
 */
@ComponentScan
public interface EmailSenderPreProcessor {
	
	public Object preprocess(Object obj, Object weather, String templateType);

}
