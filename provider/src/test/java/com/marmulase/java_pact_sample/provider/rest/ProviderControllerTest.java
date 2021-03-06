package com.marmulase.java_pact_sample.provider.rest;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.loader.PactSource;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marmulase.java_pact_sample.provider.Application;
import com.marmulase.java_pact_sample.provider.message.ProviderMessage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@RunWith(PactRunner.class)
@Provider("spring-boot-provider-http")
@PactFolder("pacts-http")
//@PactBroker(host = "localhost", port = "80")
@VerificationReports({"console"})
public class ProviderControllerTest {

	private static ConfigurableApplicationContext application;

	@TestTarget
	public final Target target = new HttpTarget(8181);

	@BeforeClass
	public static void startSpring(){
		application = SpringApplication.run(Application.class);
	}

	@State("default")
	public void toDefaultState() {
		System.out.println("Now service in default state");
	}

	@State("extra")
	public void toExtraState() {
		System.out.println("Now service in extra state");
	}

	@AfterClass
	public static void kill(){
		application.stop();
	}
}