package com.marmulase.java_pact_sample.provider.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProviderMessage {

    @JsonProperty("label")
    private String label;


    public ProviderMessage(String label) {
        this.label = label;
    }
}
