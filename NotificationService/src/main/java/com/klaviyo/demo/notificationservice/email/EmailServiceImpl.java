package com.klaviyo.demo.notificationservice.email;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klaviyo.demo.notificationservice.weatherapp.vo.Weather;

/**
 * EmailService performs the following actions.
 * 1. Calls the weatherService to get the weather updates.
 * 2. Generates the email content with the help of template.
 * 3. Sends the email to consumers.
 * 
 * @author gangadarkatakam
 *
 */
@Component
public class EmailServiceImpl {
	
	private final static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private static final String SUNNY = "Sunny";
	private static final String AVG_WEATHER_TEMPLATE = "avgweather.ftl";
	private static final String COOL_WEATHER_TEMPLATE = "coolweather.ftl";
	private static final String SUNNY_WEATHER_TEMPLATE = "sunnyweather.ftl";
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private EmailSenderPreProcessor preProcessor;
	
	@Value("${weatherapp.base.uri}")
	private String baseUri;
	
	//@Autowired 
	//private EmailSenderPostProcessor postProcessor;
	
	
	public void send(Object msg) {
		Weather weather;
		try {
			Message message = (Message) msg;
			ObjectMapper mapper = new ObjectMapper();
			RegistrationEntity entity = mapper.readValue(new String(message.getBody()), RegistrationEntity.class);
			weather = getWeather(entity.getState(), entity.getCity()+".json");
			String templateType = getEmailTemplateByWeather(weather);
			log.info("Template choosen: {}", templateType);
			Object result = preProcessor.preprocess(entity, weather, templateType);
			SubjectInterface subject = getSubjectByTemplateType(templateType);
			sendEmail(entity, result, subject);
			// postProcessor.postProcess();
		} catch (Exception e) {
			// @TODO update db with error.
			e.printStackTrace();
		}
	}

	/**
	 * @TODO Externalize using spring beans
	 * @param templateType
	 * @return
	 */
	private SubjectInterface getSubjectByTemplateType(String templateType) {
		if (SUNNY_WEATHER_TEMPLATE.equals(templateType)) {
			return new SunnyWeatherSubjectGenerator();
		}else if (COOL_WEATHER_TEMPLATE.equals(templateType)) {
			return new CoolWeatherSubjectGenerator();
		} else if (AVG_WEATHER_TEMPLATE.equals(templateType)) {
			return new AvgWeatherSubjectGenerator();
		}
		return null;
	}

	/**
	 * Return the template by the current weather condition.
	 * 1. @TODO Check Avg weather of the current year or if weather is SUNNY today, return sunny weather template.
	 * 2. Check if the day is not SUNNY & precipitation > 0, return cooler weather template.
	 * 3. If none of the above, return average weather template.
	 * @param weather
	 * @return
	 * @throws Exception 
	 */
	private String getEmailTemplateByWeather(Weather weather) throws Exception {
		if (null == weather || null == weather.getCurrentObservation()) {
			throw new Exception("Weather report is empty.");
		}
		Double precip = Double.valueOf(weather.getCurrentObservation().getPrecipTodayMetric());
		String currentWeather = weather.getCurrentObservation().getWeather();
		Double currentTemp = weather.getCurrentObservation().getTempF();
		// Check Avg weather of the current year or if weather is SUNNY today, return
		if (currentWeather.contains(SUNNY) && currentTemp > 42.0) {
			return SUNNY_WEATHER_TEMPLATE;
		} else if(!weather.getCurrentObservation().getWeather().contains(SUNNY) && (precip > 0 
				|| currentTemp < 32.0)) {
				return COOL_WEATHER_TEMPLATE;
		}
		return AVG_WEATHER_TEMPLATE;
	}

	/**
	 * Return the weather information based on the state & city.
	 * @param city
	 * @return
	 * @throws Exception 
	 */
	private Weather getWeather(String state, String city) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String weatherAppUri = constructWeatherAppBaseUri(state, city);
		Weather weather = restTemplate.getForObject(weatherAppUri, Weather.class);
		if (null == weather) {
			log.info("No response from weather app");
			//@ TODO throw custom exception. don't throw super class exception.
			throw new Exception("No response from weather app.");
		}
		System.out.println("city temp:::" +weather.getCurrentObservation().getTempF());
        log.info("Received response from weather app webservce; temp:{}", weather.getCurrentObservation().getTempF());
        return weather;
	}


	/**
	 * Generates the weatherApp uri.
	 * @param state
	 * @param city
	 * @return
	 */
	private String constructWeatherAppBaseUri(String state, String city) {
		StringBuilder url = new StringBuilder(baseUri);
		url.append(state).append("/").append(city);
		return url.toString();
	}

	/**
	 * Sends the email with the given information.
	 * @param userDetails
	 * @throws MessagingException 
	 */
	public void sendEmail(RegistrationEntity entity, Object result, SubjectInterface subject) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(entity.getEmail());
        helper.setText(result.toString(), true);
        helper.setSubject(subject.generateSubject());
        helper.setFrom("gangadhar.katakam@yahoo.com");
        emailSender.send(message);
        log.info("Succesfully delivered email to user", entity.getEmail());
	}

}
