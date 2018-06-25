package com.company.service;

import com.company.model.DataToTask3;
import com.company.model.RecordData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CreateCollections {
    private ArrayList<RecordData> allCollections;
    private ArrayList<DataToTask3> testArrayList;
    private ArrayList<DataToTask3> trainingArrayList;

    public ArrayList<DataToTask3> getTestArrayList() {
        return testArrayList;
    }

    public ArrayList<DataToTask3> getTrainingArrayList() {
        return trainingArrayList;
    }

    public void getCollection(ArrayList<RecordData> allCollections) {
        this.allCollections = allCollections;
    }

    public ArrayList<RecordData> getTestCollection() {
        ArrayList<RecordData> testCollection = new ArrayList<>();
        testArrayList = new ArrayList<>();
        Random rand = new Random();
        Double sizeCollDouble = allCollections.size() * 0.8;
        for (int i = 0; i < sizeCollDouble.intValue(); i++) {
            RecordData randomElement = allCollections.get(rand.nextInt(allCollections.size()));
            DataToTask3 m = new DataToTask3();
            double[][] matrix = randomElement.getFeatureMatrix();

            for (int j = 0; j < matrix.length; j++) {
                m.setFeatureName(randomElement.getFeatureName());
                m.setFeatureMatrix(matrix[j]);
                testArrayList.add(m);
            }

            testCollection.add(randomElement);
            allCollections.remove(randomElement);
        }
        return testCollection;
    }

    public ArrayList<RecordData> getTrainingCollection() {
        ArrayList<RecordData> trainingCollection = new ArrayList<>();
        trainingArrayList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            RecordData randomElement = allCollections.get(rand.nextInt(allCollections.size()));
            DataToTask3 m = new DataToTask3();
            double[][] matrix1 = randomElement.getFeatureMatrix();

            for (int j = 0; j < matrix1.length; j++) {
                m.setFeatureName(randomElement.getFeatureName());
                m.setFeatureMatrix(matrix1[j]);
                trainingArrayList.add(m);
            }
            trainingCollection.add(randomElement);
            allCollections.remove(randomElement);
        }
        return trainingCollection;
    }
}
