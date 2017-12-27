package com.klaviyo.demo.notificationservice.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates SummerWeather template email subject.
 * @author gangadarkatakam
 *
 */
public class SunnyWeatherSubjectGenerator implements SubjectInterface {
	
	private final static Logger log = LoggerFactory.getLogger(SunnyWeatherSubjectGenerator.class);

	@Override
	public String generateSubject() {
		log.info("Generating subject for sunny weather.");
		return "It's nice out! Enjoy a discount on us.";
	}

}
