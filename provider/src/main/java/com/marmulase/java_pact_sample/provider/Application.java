package com.marmulase.java_pact_sample.provider;

import com.marmulase.java_pact_sample.provider.rest.ProviderController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ProviderController providerController() {
		return new ProviderController();
	}
}