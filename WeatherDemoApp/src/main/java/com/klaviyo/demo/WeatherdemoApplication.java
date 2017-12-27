package com.klaviyo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WeatherDemo application makes this application as spring boot capable and 
 * makes it to run in spring context.
 * 
 * @author gangadarkatakam
 *
 */
@SpringBootApplication
public class WeatherdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherdemoApplication.class, args);
	}
}
