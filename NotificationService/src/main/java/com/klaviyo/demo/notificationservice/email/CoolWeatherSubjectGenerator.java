package com.klaviyo.demo.notificationservice.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates CoolWeather template email subject.
 * 
 * @author gangadarkatakam
 *
 */
public class CoolWeatherSubjectGenerator implements SubjectInterface {
	private final static Logger log = LoggerFactory.getLogger(CoolWeatherSubjectGenerator.class);

	@Override
	public String generateSubject() {
		log.info("Generating subject for cool weather.");
		return "Not so nice out? That's okay, enjoy a discount on us.";
	}

}
