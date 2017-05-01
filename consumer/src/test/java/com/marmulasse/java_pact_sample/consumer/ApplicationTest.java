package com.marmulasse.java_pact_sample.consumer;


import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.MockProviderConfig;
import au.com.dius.pact.model.PactFragment;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;

/**
 * Created by dpanza on 15/04/17.
 */
public class ApplicationTest {

    @Rule
    public PactProviderRule provider = new PactProviderRule("spring-boot-provider", "localhost", 8080, this);

    @Pact(state = "default", provider = "spring-boot-provider", consumer = "spring-boot-consumer")
    public PactFragment createFragment(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");

        return builder
                .given("default")
                .uponReceiving("Test Provider")
                .path("/api/provider/sample")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"label\": \"coucou\"}")
                .toFragment();
    }

    @Test
    @PactVerification("spring-boot-provider")
    public void runTest() throws IOException {
        final RestTemplate call = new RestTemplate();
        final ResponseEntity<String> forEntity = call.getForEntity(provider.getConfig().url() + "/api/provider/sample", String.class);
        Assertions.assertThat(forEntity.getBody()).isEqualTo("{\"label\": \"coucou\"}");
    }
}