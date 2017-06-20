package com.dataworks.model;

/**
 * Created by Sandeep on 5/19/17.
 */
public enum Layer {
    Landing("Landing"),
    Native("Native"),
    CONFORMANCE("Conformance"),
    MART("Mart"),
    DOMAIN("Domain"),
    ATOMIC("Atomic"),
    WORK("Work"),
    Readable("Readable");

    private String layer;

    private Layer(String layer) {
        this.layer = layer;
    }

    public String layer() {
        return layer;
    }
}
