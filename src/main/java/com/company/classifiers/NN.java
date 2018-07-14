package com.company.classifiers;

import com.company.model.DataToTask3Model;
import com.company.model.FeatureModel;
import com.company.model.NNmodel;
import com.company.service.CreateCollections;

import java.util.ArrayList;
import java.util.Collections;

public class NN {
    private float procent;

    public float getProcent() {
        return procent;
    }

    public void setProcent(float procent) {
        this.procent = procent;
    }

    public double calculateTheDistanceForTheSample(double[] coordinatesOfSample, double[] featureList) {
        double result = 0.0;
        for (int i = 0; i < featureList.length; i++) {
            result += Math.pow(featureList[i] - coordinatesOfSample[i], 2);
        }
        return Math.sqrt(result);
    }

    private String sampleSelectionForTheClassNN(ArrayList<NNmodel> listOfDistances) {
        ArrayList<Double> listOfDistancesDoubles = new ArrayList<>();
        for (NNmodel n : listOfDistances) {
            listOfDistancesDoubles.add(n.getDistance());
        }
        int index = listOfDistancesDoubles.indexOf(Collections.min(listOfDistancesDoubles));
        return listOfDistances.get(index).getFeature();
    }

    private String sampleSelectionForTheClasskNN(ArrayList<NNmodel> listOfDistances, int n) {
        ArrayList<Integer> indexesOfArray = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();
        ArrayList<String> featureNames = new ArrayList<>();
        int i = 0;
        for (NNmodel listOfDistance : listOfDistances) {
            distances.add(listOfDistance.getDistance());
        }

        while (i < n) {
            int minIndex = distances.indexOf(Collections.min(distances));
            indexesOfArray.add(minIndex);
            distances.remove(minIndex);
            i++;
        }

        for (Integer index : indexesOfArray) {
            featureNames.add(listOfDistances.get(index).getFeature());
        }
        return getMostFrequentElement(featureNames);
    }

    private String getMostFrequentElement(ArrayList<String> featureNames) {
        String element = null;
        int count = 0;
        for (int i = 0; i < featureNames.size(); i++) {
            String tempElement = featureNames.get(i);
            int tempCount = 0;
            for (String featureName : featureNames) {
                if (featureName.equals(tempElement)) {
                    tempCount++;
                }
            }
            if (tempCount > count) {
                element = tempElement;
                count = tempCount;
            }
        }
        return element;
    }

    public void testClassNN(ArrayList<FeatureModel> arrayList) {
        NM nm = new NM();
        System.out.println("\n+++++++++++Zadanie 3+++++++++++");
        CreateCollections createCollections = new CreateCollections();
        createCollections.getCollection(arrayList);
        createCollections.createArrayWithRecords();
        createCollections.createArrayWithRecordsTraining();
        createCollections.createArrayWithRecordsTest();
        testNN(createCollections.getArrayListTraining(), createCollections.getArrayListTest());
        testKNN(createCollections.getArrayListTraining(), createCollections.getArrayListTest(), 4);
        //nm.testNM(createCollections.getArrayListTraining(), createCollections.getArrayListTest()); <-- check this out
}

    public void testNN(ArrayList<DataToTask3Model> arrayListTraining, ArrayList<DataToTask3Model> arrayListTest) {
        ArrayList<NNmodel> distancesSamples = new ArrayList<>();
        ArrayList<Boolean> trueArray = new ArrayList<>();
        ArrayList<Boolean> array = new ArrayList<>();
        for (int i = 0; i < arrayListTraining.size() - 1; i++) {
            for (int j = 0; j < arrayListTest.size() - 1; j++) {
                double distance = calculateTheDistanceForTheSample(
                        arrayListTraining.get(i).getFeatureMatrix(),
                        arrayListTest.get(j).getFeatureMatrix());
                distancesSamples.add(new NNmodel(distance, arrayListTest.get(j).getFeatureName()));
            }

            if (sampleSelectionForTheClassNN(distancesSamples).equals(arrayListTraining.get(i).getFeatureName())) {
                trueArray.add(sampleSelectionForTheClassNN(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
                array.add(sampleSelectionForTheClassNN(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
            } else {
                array.add(sampleSelectionForTheClassNN(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
            }

            distancesSamples.clear();
        }
        Integer a = trueArray.size();
        Integer b = array.size();
        float procent = a.floatValue() / b.floatValue();
        setProcent(procent);
        System.out.println("Wynik dla NN: " + procent * 100 + "%");
    }

    public void testKNN(ArrayList<DataToTask3Model> arrayListTraining, ArrayList<DataToTask3Model> arrayListTest, int n) {
        ArrayList<NNmodel> distancesSamples = new ArrayList<>();
        ArrayList<Boolean> trueArray = new ArrayList<>();
        ArrayList<Boolean> array = new ArrayList<>();
        for (int i = 0; i < arrayListTraining.size() - 1; i++) {
            for (int j = 0; j < arrayListTest.size() - 1; j++) {
                double distance = calculateTheDistanceForTheSample(
                        arrayListTraining.get(i).getFeatureMatrix(),
                        arrayListTest.get(j).getFeatureMatrix());
                distancesSamples.add(new NNmodel(distance, arrayListTest.get(j).getFeatureName()));
            }

            if (sampleSelectionForTheClasskNN(distancesSamples, n).equals(arrayListTraining.get(i).getFeatureName())) {
                trueArray.add(sampleSelectionForTheClasskNN(distancesSamples, n).equals(arrayListTraining.get(i).getFeatureName()));
                array.add(sampleSelectionForTheClasskNN(distancesSamples, n).equals(arrayListTraining.get(i).getFeatureName()));
            } else {
                array.add(sampleSelectionForTheClasskNN(distancesSamples, n).equals(arrayListTraining.get(i).getFeatureName()));
            }

            distancesSamples.clear();
        }
        Integer a = trueArray.size();
        Integer b = array.size();
        float procent = a.floatValue() / b.floatValue();
        setProcent(procent);
        System.out.println("Wynik dla k-NN, gdzie k = " + n + " : " + procent * 100 + "%");
    }
}
