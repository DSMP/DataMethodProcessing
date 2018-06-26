package com.company.model;

public class DataToTask3 {
    private String featureName;
    private double[] featureMatrix;

    public DataToTask3() {
    }

    public DataToTask3(String featureName, double[] featureMatrix) {
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
