package com.company.model;

public class FeatureModel {
    private String featureName;
    private double[][] featureMatrix;

    public FeatureModel(String featureName, double[][] featureMatrix) {
        this.featureName = featureName;
        this.featureMatrix = featureMatrix;
    }

    public String getFeatureName() {
        return featureName;
    }

    public double[][] getFeatureMatrix() {
        return featureMatrix;
    }
}
