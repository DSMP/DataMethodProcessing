package com.company.classifiers;

import com.company.model.NNmodel;
import com.company.model.RecordData;
import com.company.service.CreateCollections;

import java.util.ArrayList;
import java.util.Collections;

public class NN {
    public double calculateTheDistanceForTheSample(double[] coordinatesOfSample, double[] featureList) {
        double result = 0.0;
        for (int i = 0; i < featureList.length; i++) {
            result += Math.pow(featureList[i] - coordinatesOfSample[i], 2);
        }
        return Math.sqrt(result);
    }

    public String sampleSelectionForTheClassNN(ArrayList<NNmodel> listOfDistances) {
        ArrayList<Double> listOfDistancesDoubles = new ArrayList<>();
        for (NNmodel n : listOfDistances) {
            listOfDistancesDoubles.add(n.getDistance());
        }
        int index = listOfDistancesDoubles.indexOf(Collections.min(listOfDistancesDoubles));
        return listOfDistances.get(index).getFeature();
    }

    public void sampleSelectionForTheClasskNN(ArrayList<NNmodel> listOfDistances, int n) {
        int i = 0;
        ArrayList<Double> distances = new ArrayList<Double>();
        for (NNmodel listOfDistance : listOfDistances) {
            distances.add(listOfDistance.getDistance());
        }

        ArrayList<Integer> indexesOfArray = new ArrayList<Integer>();

        while (i < n) {
            int minIndex = distances.indexOf(Collections.min(distances));
            indexesOfArray.add(minIndex);
            distances.remove(minIndex);
            i++;
        }

        for (Integer index : indexesOfArray) {
            System.out.println(listOfDistances.get(index).getFeature());
        }
    }

    public void testNN(ArrayList<RecordData> arrayList) {
        CreateCollections createCollections = new CreateCollections();
        createCollections.getCollection(arrayList);
        createCollections.createArrayWithRecords();
        createCollections.createArrayWithRecordsTraining();
        createCollections.createArrayWithRecordsTest();
        ArrayList<NNmodel> distancesSamples = new ArrayList<>();
        ArrayList<Boolean> trueArray = new ArrayList<>();
        ArrayList<Boolean> array = new ArrayList<>();
        for (int i = 0; i < createCollections.getArrayListTraining().size() - 1; i++) {
            for (int j = 0; j < createCollections.getArrayListTest().size() - 1; j++) {
                double distance = calculateTheDistanceForTheSample(
                        createCollections.getArrayListTraining().get(i).getFeatureMatrix(),
                        createCollections.getArrayListTest().get(j).getFeatureMatrix());
                distancesSamples.add(new NNmodel(distance, createCollections.getArrayListTest().get(j).getFeatureName()));
            }

            if (sampleSelectionForTheClassNN(distancesSamples).equals(createCollections.getArrayListTraining().get(i).getFeatureName())) {
                trueArray.add(sampleSelectionForTheClassNN(distancesSamples).equals(createCollections.getArrayListTraining().get(i).getFeatureName()));
                array.add(sampleSelectionForTheClassNN(distancesSamples).equals(createCollections.getArrayListTraining().get(i).getFeatureName()));
            } else {
                array.add(sampleSelectionForTheClassNN(distancesSamples).equals(createCollections.getArrayListTraining().get(i).getFeatureName()));
            }

            distancesSamples.clear();
        }
        Integer a = trueArray.size();
        Integer b = array.size();
        float procent = a.floatValue() / b.floatValue();
        System.out.println("Wynik dla NN: " + procent * 100 + "%");
    }
}
