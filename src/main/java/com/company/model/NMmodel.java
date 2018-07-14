package com.company.model;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class NMmodel {
    private String featureName;
    private double[] vector;

    public NMmodel(String featureName, double[] vector) {
        this.featureName = featureName;
        this.vector = vector;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }
}
