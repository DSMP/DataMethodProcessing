package com.company.model;

public class NMAvgModel {
    private String featureName;
    private double[] centerPointfeature;

    public NMAvgModel(String featureName, double[] avgFeature) {
        this.featureName = featureName;
        this.centerPointfeature = avgFeature;
    }

    public String getFeatureName() {

        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public double[] getCenterPointfeature() {
        return centerPointfeature;
    }

    public void setCenterPointfeature(double[] centerPointfeature) {
        this.centerPointfeature = centerPointfeature;
    }
}
