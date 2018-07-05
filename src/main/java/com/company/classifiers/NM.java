package com.company.classifiers;

import com.company.model.DataToTask3Model;
import com.company.model.NMmodel;
import com.company.model.NNmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NM {
    private float procent;

    public float getProcent() {
        return procent;
    }

    public void setProcent(float procent) {
        this.procent = procent;
    }

    public double calculateVector(double[] featureList) {
        double result = 0.0;
        for (int i = 0; i < featureList.length; i++) {
            result += featureList[i];
        }
        return result / featureList.length;
    }

    public double calculateTheDistanceForTheSample(ArrayList<NMmodel> vector,
                                                   double[] coordinatesOfSample) {
        double result = 0.0;
        for (int i = 0; i < coordinatesOfSample.length; i++) {
            result += Math.pow(coordinatesOfSample[i] - vector.get(i).getVector().get(i).getDistance(), 2);
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

    public Set<String> getNames(ArrayList<DataToTask3Model> arrayListTest) {
        Set<String> featureNamesInArray = new HashSet<>();
        for (int i = 0; i < arrayListTest.size(); i++) {
            featureNamesInArray.add(arrayListTest.get(i).getFeatureName());
        }
        return featureNamesInArray;
    }

    public void testNM(ArrayList<DataToTask3Model> arrayListTraining, ArrayList<DataToTask3Model> arrayListTest) {
        ArrayList<NNmodel> distancesSamples = new ArrayList<>();
        ArrayList<NMmodel> gggg = new ArrayList<>();

        ArrayList<Boolean> trueArray = new ArrayList<>();
        ArrayList<Boolean> array = new ArrayList<>();
        Set<String> featureNamesInArray = getNames(arrayListTest);

        Object[] names = featureNamesInArray.toArray();

        for (int i = 0; i < names.length; i++) {
            ArrayList<NNmodel> vectorMediana = new ArrayList<>();
            ArrayList<DataToTask3Model> arr = new ArrayList<>();
            for (int j = 0; j < arrayListTest.size(); j++) {
                if (names[i] == arrayListTest.get(j).getFeatureName()) {
                    arr.add(arrayListTest.get(i));
                }
            }
            double[][] matrix = new double[arr.size()][64];
            for (int j = 0; j < arr.size(); j++) {
                matrix[j] = arr.get(j).getFeatureMatrix();
            }

            for (int j = 0; j < 64; j++) {
                double[] featureList = new double[arr.size()];
                for (int x = 0; x < arr.size(); x++) {
                    featureList[x] = matrix[x][j];
                }
                vectorMediana.add(new NNmodel(calculateVector(featureList), arr.get(0).getFeatureName()));
            }

            gggg.add(new NMmodel(arr.get(0).getFeatureName(), vectorMediana));
            System.out.println(calculateTheDistanceForTheSample(gggg, arrayListTest.get(i).getFeatureMatrix()));
        }


//        for (int i = 0; i < arrayListTraining.size() - 1; i++) {
//            for (int j = 0; j < arrayListTest.size() - 1; j++) {
//                double distance = calculateTheDistanceForTheSample(arrayListTraining.get(i).getFeatureMatrix(),
//                        arrayListTest.get(j).getFeatureMatrix());
//                distancesSamples.add(new NNmodel(distance, arrayListTest.get(j).getFeatureName()));
//            }
//            if (sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName())) {
//                trueArray.add(sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
//                array.add(sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
//            } else {
//                array.add(sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
//            }
//            distancesSamples.clear();
//        }

        Integer a = trueArray.size();
        Integer b = array.size();
        float procent = a.floatValue() / b.floatValue();
        setProcent(procent);
        System.out.println("Wynik dla NM: " + procent * 100 + "%");
    }
}
