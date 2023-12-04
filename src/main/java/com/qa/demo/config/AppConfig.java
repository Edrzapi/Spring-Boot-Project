package com.qa.demo.config;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

	@Bean
	public String time() {
		return "Application started: " + LocalTime.now();
	}

	@Bean
	@Scope("prototype")
	public ModelMapper mapper() {
		return new ModelMapper();
	}
}