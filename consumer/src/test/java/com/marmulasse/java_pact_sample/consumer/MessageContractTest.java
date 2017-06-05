package com.marmulasse.java_pact_sample.consumer;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.MessagePactProviderRule;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.model.v3.messaging.MessagePact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marmulasse.java_pact_sample.consumer.message.ConsumerMessage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MessageContractTest {

    @Rule
    public MessagePactProviderRule mockProvider = new MessagePactProviderRule("spring-boot-provider", this);

    @Pact(state = "default", provider = "spring-boot-provider-message", consumer = "spring-boot-consumer-message")
    public MessagePact createPact(MessagePactBuilder builder) {

        PactDslJsonBody body = new PactDslJsonBody();
        body.stringType("label");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("contentType", "application/json");

        return builder.given("default")
                .expectsToReceive("a test message")
                .withMetadata(metadata)
                .withContent(body)
                .toPact();
    }


    @Test
    @PactVerification("spring-boot-provider-message")
    public void test() throws Exception {
        ConsumerMessage consumerMessage = new ObjectMapper().readValue(mockProvider.getMessage(), ConsumerMessage.class);
        Assert.assertNotNull(consumerMessage.getLabel());
    }

}