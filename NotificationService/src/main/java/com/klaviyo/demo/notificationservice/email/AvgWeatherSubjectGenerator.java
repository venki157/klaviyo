package com.klaviyo.demo.notificationservice.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates AvgWeather template email subject.
 * @author gangadarkatakam
 *
 */
public class AvgWeatherSubjectGenerator implements SubjectInterface {
	private final static Logger log = LoggerFactory.getLogger(AvgWeatherSubjectGenerator.class);

	@Override
	public String generateSubject() {
		log.info("Generating subject for avg weather.");
		return "Enjoy a discount on us.";
	}

}
