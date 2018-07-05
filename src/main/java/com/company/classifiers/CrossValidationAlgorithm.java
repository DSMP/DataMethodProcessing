package com.company.classifiers;

import com.company.model.DataToTask3Model;

import java.util.ArrayList;
import java.util.Random;

public class CrossValidationAlgorithm {
    private ArrayList<DataToTask3Model> arrayListWithVectors;
    private Integer numberOfParts;
    private ArrayList<ArrayList<DataToTask3Model>> arrayListWithParts;

    public CrossValidationAlgorithm(ArrayList<DataToTask3Model> arrayListWithVectors, Integer numberOfParts) {
        this.arrayListWithVectors = arrayListWithVectors;
        this.numberOfParts = numberOfParts;
    }

    public ArrayList<ArrayList<DataToTask3Model>> divideArrayToParts() {
        ArrayList<ArrayList<DataToTask3Model>> parts = new ArrayList<>();
        arrayListWithParts = new ArrayList<>();
        Random rand = new Random();
        Double sizeCollDouble = (double) (arrayListWithVectors.size() / numberOfParts);
        while (numberOfParts > 0) {
            ArrayList<DataToTask3Model> part = new ArrayList<>();
            for (int i = 0; i < sizeCollDouble.intValue(); i++) {
                DataToTask3Model dataToTask3Model = arrayListWithVectors.get(rand.nextInt(arrayListWithVectors.size()));
                part.add(dataToTask3Model);
                arrayListWithVectors.remove(dataToTask3Model);
            }
            parts.add(part);
            numberOfParts--;
        }
        arrayListWithParts.addAll(parts);
        return parts;
    }

    public void qualify(String selector) {
        ArrayList<DataToTask3Model> arrayListTraining = new ArrayList<>();
        ArrayList<DataToTask3Model> arrayListTest = new ArrayList<>();
        NN nn = new NN();
        NM nm = new NM();
        double proc = 0.0;
        int arraySize = arrayListWithParts.size();
        for (int i = 0; i < arraySize; i++) {
            ArrayList<ArrayList<DataToTask3Model>> arrayList = new ArrayList<>(arrayListWithParts);
            arrayListTraining = arrayList.get(i);
            arrayList.remove(i);
            for (ArrayList<DataToTask3Model> s : arrayList) {
                arrayListTest.addAll(s);
            }
            switch (selector) {
                case "NN":
                    nn.testNN(arrayListTest, arrayListTraining);
                    proc += nn.getProcent();
                    break;
                case "KNN":
                    nn.testKNN(arrayListTest, arrayListTraining, 3);
                    proc += nn.getProcent();
                    break;
                case "NM":
                    nm.testNM(arrayListTest, arrayListTraining);
                    proc += nm.getProcent();
                    break;
            }

        }
        System.out.println("Result for all tests: " + proc / arraySize * 100 + "%");
    }
}
