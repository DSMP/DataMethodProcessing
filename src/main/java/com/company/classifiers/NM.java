package com.company.classifiers;

import com.company.model.DataToTask3Model;
import com.company.model.NNmodel;

import java.util.ArrayList;
import java.util.Collections;

public class NM {
    public double calculateVector(double[] featureList) {
        double result = 0.0;
        for (int i = 0; i < featureList.length; i++) {
            result += featureList[i];
        }
        return result / featureList.length;
    }

    public double calculateTheDistanceForTheSample(double[] vector,
                                                   double[] coordinatesOfSample) {
        double result = 0.0;
        for (int i = 0; i < coordinatesOfSample.length; i++) {
            result += Math.pow(coordinatesOfSample[i] - vector[i], 2);
        }
        return Math.sqrt(result);
    }

    private String sampleSelectionForTheClassNM(ArrayList<NNmodel> listOfDistances) {
        ArrayList<Double> listOfDistancesDoubles = new ArrayList<>();
        for (NNmodel n : listOfDistances) {
            listOfDistancesDoubles.add(n.getDistance());
        }
        int index = listOfDistancesDoubles.indexOf(Collections.min(listOfDistancesDoubles));
        return listOfDistances.get(index).getFeature();
    }

    public void testNM(ArrayList<DataToTask3Model> arrayListTraining, ArrayList<DataToTask3Model> arrayListTest) {
        ArrayList<NNmodel> distancesSamples = new ArrayList<>();
        ArrayList<Boolean> trueArray = new ArrayList<>();
        ArrayList<Boolean> array = new ArrayList<>();

        for (int i = 0; i < arrayListTraining.size() - 1; i++) {
            for (int j = 0; j < arrayListTest.size() - 1; j++) {
                double distance = calculateTheDistanceForTheSample(arrayListTraining.get(i).getFeatureMatrix(),
                        arrayListTest.get(j).getFeatureMatrix());
                distancesSamples.add(new NNmodel(distance, arrayListTest.get(j).getFeatureName()));
            }
            if (sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName())) {
                trueArray.add(sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
                array.add(sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
            } else {
                array.add(sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
            }
            distancesSamples.clear();
        }

        Integer a = trueArray.size();
        Integer b = array.size();
        float procent = a.floatValue() / b.floatValue();
        System.out.println("Wynik dla NM: " + procent * 100 + "%");
    }

    public double getProcent() {
        return 0;
    }
}
