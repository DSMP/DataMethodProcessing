package com.company.classifiers;

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

    public void testNN(ArrayList<RecordData> arrayList) {
        CreateCollections createCollections = new CreateCollections();
        createCollections.getCollection(arrayList);
        for (int i = 0; i < createCollections.getTestCollection().size() - 1; i++) {
            for (int j = 0; j < createCollections.getTrainingCollection().size() - 1; j++) {
                double[][] matrix = createCollections.getTestCollection().get(j).getFeatureMatrix();
//                for (int p = 0; p < matrix)
//                    nn.calculateTheDistanceForTheSample(,)
            }
        }
        createCollections.getTestCollection();
        createCollections.getTrainingCollection();
    }
}
