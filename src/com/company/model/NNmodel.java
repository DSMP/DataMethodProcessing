package com.company.model;

public class NNmodel {
    private double distance;
    private String feature;

    public NNmodel(double distance, String feature) {
        this.distance = distance;
        this.feature = feature;
    }

    public double getDistance() {
        return distance;
    }

    public String getFeature() {
        return feature;
    }
}
