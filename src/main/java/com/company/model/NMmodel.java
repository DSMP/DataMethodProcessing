package com.company.model;

import java.util.ArrayList;

public class NMmodel {
    private String featureName;
    private ArrayList<NNmodel>  vector;

    public NMmodel(String featureName, ArrayList<NNmodel> vector) {
        this.featureName = featureName;
        this.vector = vector;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public ArrayList<NNmodel> getVector() {
        return vector;
    }

    public void setVector(ArrayList<NNmodel> vector) {
        this.vector = vector;
    }
}
