package com.marmulasse.java_pact_sample.consumer;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;
import com.marmulasse.java_pact_sample.consumer.rest.ConsumerObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpContractTest {

    @Rule
    public PactProviderRule provider = new PactProviderRule("spring-boot-provider-http", "localhost", 8080, PactSpecVersion.V3, this);

    @Pact(state = "default", provider = "spring-boot-provider-http", consumer = "spring-boot-consumer-http")
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
                .body(new PactDslJsonBody()
                        .stringType("label"))
                .toFragment();
    }

    @Test
    @PactVerification("spring-boot-provider-http")
    public void runTest() throws IOException {
        final RestTemplate call = new RestTemplate();
        final ResponseEntity<ConsumerObject> forEntity = call.getForEntity(provider.getConfig().url() + "/api/provider/sample", ConsumerObject.class);

        Assert.assertNotNull(forEntity.getBody().getLabel());
    }
}
