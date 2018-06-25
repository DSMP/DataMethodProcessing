package com.company.classifiers;

import com.company.model.DataToTask3;
import com.company.model.NNmodel;
import com.company.model.RecordData;
import com.company.service.CreateCollections;

import java.util.ArrayList;
import java.util.Collections;

public class NN {
    public double calculateTheDistanceForTheSample(double[] coordinatesOfSample,
                                                    double[] featureList) {
        double result = 0.0;
        for (int i = 0; i < featureList.length; i++) {
            result += Math.pow(featureList[i] - coordinatesOfSample[i], 2);
        }

        return Math.sqrt(result);
    }

    public double sampleSelectionForTheClassNN(ArrayList<NNmodel> listOfDistances) {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (NNmodel d : listOfDistances) {
            arrayList.add(d.getDistance());
        }
        return listOfDistances.indexOf(Collections.min(arrayList));
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
        createCollections.getTestCollection();
        createCollections.getTrainingCollection();
        ArrayList<DataToTask3> testArray = createCollections.getTestArrayList();
        ArrayList<DataToTask3> trainingArray = createCollections.getTrainingArrayList();
        ArrayList<NNmodel> distancesSamples = new ArrayList<>();
        for (int i = 0; i < testArray.size() - 1; i++) {
            for (int j = 0; j < trainingArray.size() - 1; j++) {
                double distance = calculateTheDistanceForTheSample(trainingArray.get(j).getFeatureMatrix(), testArray.get(i).getFeatureMatrix());
                distancesSamples.add(new NNmodel(distance, testArray.get(i).getFeatureName()));
                System.out.println(distance);
            }
        }
        System.out.println(sampleSelectionForTheClassNN(distancesSamples));
        //sampleSelectionForTheClasskNN()
    }
}
