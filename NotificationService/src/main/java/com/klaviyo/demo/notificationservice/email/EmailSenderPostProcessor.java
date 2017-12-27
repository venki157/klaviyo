package com.klaviyo.demo.notificationservice.email;

/**
 * PostProcessor to book keep the email notifications send to users.
 * or we can send event back to weather app to update database.
 * @author gangadarkatakam
 *
 */
public interface EmailSenderPostProcessor {
	/**
	 * Post process the results.
	 */
	public void postProcess();

}
