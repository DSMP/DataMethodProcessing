package com.company.model;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class NMmodel {
    private String featureName;
    private SimpleMatrix vector;

    public NMmodel(String featureName, SimpleMatrix vector) {
        this.featureName = featureName;
        this.vector = vector;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public SimpleMatrix getVector() {
        return vector;
    }

    public void setVector(SimpleMatrix vector) {
        this.vector = vector;
    }
}
