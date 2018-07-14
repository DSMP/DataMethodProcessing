package com.company.model;

public class NMAvgModelExt extends NMAvgModel {
    private String featureNameOld;

    public String getFeatureNameOld() {
        return featureNameOld;
    }

    public void setFeatureNameOld(String featureNameOld) {
        this.featureNameOld = featureNameOld;
    }

    public NMAvgModelExt(String featureNameOld, String featureName, double avgFeature) {
        super(featureName, avgFeature);
        this.featureNameOld = featureNameOld;
    }
}
