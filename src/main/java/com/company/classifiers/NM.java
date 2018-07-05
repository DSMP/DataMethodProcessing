package com.company.classifiers;

import com.company.model.DataToTask3Model;
import com.company.model.NMmodel;
import com.company.model.NNmodel;
import org.ejml.simple.SimpleMatrix;

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

    public double[] calculateVector(SimpleMatrix featureList) {
        double[] result = new double[featureList.numCols()];
        double[] result1 = new double[featureList.numCols()];
        for (int i = 0; i < featureList.numCols(); i++) {
            for (int j = 0; j < featureList.numRows(); j++) {
                result[j] = featureList.get(j, i);
            }

            for (int j = 0; j < result.length; j++) {
                result1[i] = result[j] / featureList.numRows();
            }
        }
        return result1;
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

    public Set<String> getNames(ArrayList<DataToTask3Model> arrayListTest) {
        Set<String> featureNamesInArray = new HashSet<>();
        for (int i = 0; i < arrayListTest.size(); i++) {
            featureNamesInArray.add(arrayListTest.get(i).getFeatureName());
        }
        return featureNamesInArray;
    }

    public SimpleMatrix convertToSimpleMatrix(ArrayList<DataToTask3Model> arrayListWithRecords) {
        double[][] matrix = new double[arrayListWithRecords.size()][];
        for (int i = 0; i < arrayListWithRecords.size(); i++) {
            matrix[i] = arrayListWithRecords.get(i).getFeatureMatrix();
        }
        return new SimpleMatrix(matrix);
    }

    public void testNM(ArrayList<DataToTask3Model> arrayListTraining, ArrayList<DataToTask3Model> arrayListTest) {
        ArrayList<NNmodel> distancesSamples = new ArrayList<>();
        ArrayList<NMmodel> gggg = new ArrayList<>();

        ArrayList<Boolean> trueArray = new ArrayList<>();
        ArrayList<Boolean> array = new ArrayList<>();
        Set<String> featureNamesInArray = getNames(arrayListTest);

        Object[] names = featureNamesInArray.toArray();

        for (int i = 0; i < names.length; i++) {
            ArrayList<DataToTask3Model> arr = new ArrayList<>();
            for (int j = 0; j < arrayListTest.size(); j++) {
                if (names[i] == arrayListTest.get(j).getFeatureName()) {
                    arr.add(arrayListTest.get(i));
                }
            }
            gggg.add(new NMmodel(arr.get(0).getFeatureName(), convertToSimpleMatrix(arr)));
            double[][] matrix = new double[arr.size()][64];
            for (int j = 0; j < arr.size(); j++) {
                matrix[j] = arr.get(j).getFeatureMatrix();
//                calculateVector();
            }

        }

        System.out.println(calculateVector(gggg.get(0).getVector()).length);

        for (int i = 0; i < gggg.size() - 1; i++) {
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
        setProcent(procent);
//        System.out.println("Wynik dla NM: " + procent * 100 + "%");
    }
}
