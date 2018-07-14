package com.company.model;

public class NMAvgModel {
    private String featureName;
    private double avgFeature;

    public NMAvgModel(String featureName, double avgFeature) {
        this.featureName = featureName;
        this.avgFeature = avgFeature;
    }

    public String getFeatureName() {

        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public double getAvgFeature() {
        return avgFeature;
    }

    public void setAvgFeature(double avgFeature) {
        this.avgFeature = avgFeature;
    }
}
