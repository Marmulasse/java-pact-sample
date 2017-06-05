package com.marmulase.java_pact_sample.provider.rest;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.AmqpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marmulase.java_pact_sample.provider.message.ProviderMessage;
import groovy.json.JsonOutput;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.Collections;

@RunWith(PactRunner.class)
@Provider("spring-boot-provider-message")
@PactFolder("pacts-message")
//@PactFolder("message-pact")
//@PactBroker(host = "localhost", port = "80")
@VerificationReports({"console"})
public class ProviderMessageTest {

	@TestTarget
	public final Target target = new AmqpTarget(Collections.singletonList("com.marmulase.java_pact_sample.provider.rest.*"));
//	public final Target target = new AmqpTarget();

	@BeforeClass //Method will be run once: before whole contract test suite
	public static void setUpService() {
		//Run DB, create schema
		//Run service
		//...
	}

	@Before //Method will be run before each test of interaction
	public void before() {
		// Message data preparation
		// ...
	}

	@State("default")
	public void toDefaultState() {
		System.out.println("Now service in default state");
	}

	@PactVerifyProvider("a test message")
	public String verifyMessageForOrder() throws JsonProcessingException {
		ProviderMessage providerMessage = new ProviderMessage("myLabel");

		return new ObjectMapper().writeValueAsString(providerMessage);
	}

}