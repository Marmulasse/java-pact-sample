package com.marmulasse.java_pact_sample.consumer;


import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.PactError;
import au.com.dius.pact.consumer.TestRun;
import au.com.dius.pact.consumer.VerificationResult;
import au.com.dius.pact.model.MockProviderConfig;
import au.com.dius.pact.model.PactFragment;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dpanza on 15/04/17.
 */
public class ApplicationTest {

	@Test
	public void testPact() {
		PactFragment pactFragment = ConsumerPactBuilder
				.consumer("Some Consumer")
				.hasPactWith("Some Provider")
				.uponReceiving("a request to say Hello")
				.path("/hello")
				.method("POST")
				.body("{\"name\": \"harry\"}")
				.willRespondWith()
				.status(200)
				.body("{\"hello\": \"harry\"}")
				.toFragment();
	}

}