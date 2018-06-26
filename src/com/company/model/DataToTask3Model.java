package com.company.model;

public class DataToTask3Model {
    private String featureName;
    private double[] featureMatrix;

    public DataToTask3Model() {
    }

    public DataToTask3Model(String featureName, double[] featureMatrix) {
        this.featureName = featureName;
        this.featureMatrix = featureMatrix;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public double[] getFeatureMatrix() {
        return featureMatrix;
    }

    public void setFeatureMatrix(double[] featureMatrix) {
        this.featureMatrix = featureMatrix;
    }
}
