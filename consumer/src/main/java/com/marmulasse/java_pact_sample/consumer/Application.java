package com.marmulasse.java_pact_sample.consumer;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}