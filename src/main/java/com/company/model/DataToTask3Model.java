package com.company.model;

public class DataToTask3Model {
    private String featureName;
    private double[] featureList;

    public DataToTask3Model() {
    }

    public DataToTask3Model(String featureName, double[] featureMatrix) {
        this.featureName = featureName;
        this.featureList = featureMatrix;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public double[] getFeatureList() {
        return featureList;
    }

    public void setFeatureList(double[] featureList) {
        this.featureList = featureList;
    }
}
