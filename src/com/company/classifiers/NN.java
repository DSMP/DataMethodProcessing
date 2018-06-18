package com.company.classifiers;

import com.company.model.NNmodel;

import java.util.ArrayList;
import java.util.Collections;

public class NN {
    public double calculateTheDistanceForTheSample(ArrayList<Double> coordinatesOfSample,
                                                   ArrayList<Double> featureList) {
        double result = 0.0;
        for (int i = 0; i < featureList.size(); i++) {
            result += Math.pow(featureList.get(i) - coordinatesOfSample.get(i), 2);
        }
        return Math.sqrt(result);
    }

    public int sampleSelectionForTheClassNN(ArrayList<Double> listOfDistances) {
        return listOfDistances.indexOf(Collections.min(listOfDistances));
    }

    public void sampleSelectionForTheClasskNN(ArrayList<NNmodel> listOfDistances, int n) {
        int i = 0;
        ArrayList<Double> distances = new ArrayList<Double>();
        for (NNmodel listOfDistance : listOfDistances) {
            distances.add(listOfDistance.getDistance());
        }

        ArrayList<Integer> indexesOfArray = new ArrayList<Integer>();

        while(i < n) {
            int minIndex = distances.indexOf(Collections.min(distances));
            indexesOfArray.add(minIndex);
            distances.remove(minIndex);
            i++;
        }

        for (Integer index : indexesOfArray) {
            System.out.println(listOfDistances.get(index).getFeature());
        }
    }
}
