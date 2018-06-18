package com.company.classifiers;

import java.util.ArrayList;

public class NM {
    public double calculateTheDistanceForTheSample(ArrayList<Double> coordinatesOfSample,
                                                   ArrayList<Double> featureList) {
        double result = 0.0;
        for (int i = 0; i < featureList.size(); i++) {
            result += Math.pow(featureList.get(i) - coordinatesOfSample.get(i), 2);
        }
        return Math.sqrt(result);
    }
}
