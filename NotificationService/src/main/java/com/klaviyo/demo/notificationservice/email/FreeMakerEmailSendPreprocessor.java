package com.klaviyo.demo.notificationservice.email;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.klaviyo.demo.notificationservice.weatherapp.vo.Weather;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Preprocessor responsible to generate the email subject before sending email to user.
 * 
 * @TODO exception handling.
 * 
 * @author gangadarkatakam
 *
 */
@Component
public class FreeMakerEmailSendPreprocessor implements EmailSenderPreProcessor {
	
	private final static Logger log = LoggerFactory.getLogger(FreeMakerEmailSendPreprocessor.class);
	
	@Autowired
    private Configuration freemarkerConfig;

	@Override
	public Object preprocess(Object obj, Object weatherObj, String templateType) {

		Template t;
		String emailContent = null;
		try {
			t = freemarkerConfig.getTemplate(templateType);
			Map<String, String> modelMap = new HashMap<String, String>();
			Weather weather = (Weather) weatherObj;
			RegistrationEntity entity = (RegistrationEntity) obj;
			modelMap.put("temp", weather.getCurrentObservation().getTempF().toString());
			modelMap.put("city", entity.getCity());
			emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(t, modelMap);
		} catch (IOException | TemplateException e) {
			log.error("Problem in generating email template", e);
			e.printStackTrace();
		}
        
		return emailContent;
	}

	

}
