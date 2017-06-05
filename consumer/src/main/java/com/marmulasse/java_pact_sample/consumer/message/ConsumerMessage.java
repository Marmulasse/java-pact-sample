package com.marmulasse.java_pact_sample.consumer.message;

/**
 * Created by dpanza on 04/06/17.
 */
public class ConsumerMessage {

    private String label;

    public ConsumerMessage() {
    }

    public ConsumerMessage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
