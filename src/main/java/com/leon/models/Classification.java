package com.leon.models;

public enum Classification {
    ORDER("Order");
    private final String classification;
    Classification(String classification) {
        this.classification = classification;
    }
    public String getClassification() {
        return classification;
    }
}
