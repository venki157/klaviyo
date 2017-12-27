package com.klaviyo.demo.rest.services.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmailValidator is utility class to validate the email in proper format.
 * 
 * @author gangadarkatakam
 *
 */
public class EmailValidator {
	
	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	
	/**
	 * Validates the email pattern.
	 * @param email - email string.
	 * @return true - if the email is in valid format.
	 *         false - If the email is invalid pattern
	 */
	public static boolean isValidEmail(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
