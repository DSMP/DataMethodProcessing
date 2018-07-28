package com.company.service;

import com.company.model.DataToTask3Model;
import com.company.model.FeatureModel;

import java.util.ArrayList;
import java.util.Random;

public class CreateCollections {
    private ArrayList<FeatureModel> allCollections;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++TRAINING AND TEST COLLECTIONS++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private ArrayList<DataToTask3Model> arrayList;
    private ArrayList<DataToTask3Model> arrayListTest;
    private ArrayList<DataToTask3Model> arrayListTraining;

    public ArrayList<DataToTask3Model> getArrayListTest() {
        return arrayListTest;
    }

    public ArrayList<DataToTask3Model> getArrayListTraining() {
        return arrayListTraining;
    }

    public ArrayList<DataToTask3Model> getArrayList() {
        return arrayList;
    }

    public void getCollection(ArrayList<FeatureModel> allCollections) {
        this.allCollections = allCollections;
    }

    public void createArrayWithRecords() {
        arrayList = new ArrayList<>();
        for (FeatureModel featureModel : allCollections) {
            for (int j = 0; j < featureModel.getFeatureMatrix().length; j++) {
                arrayList.add(new DataToTask3Model(featureModel.getFeatureName(), featureModel.getFeatureMatrix()[j]));
            }
        }
    }

    public void createArrayWithRecordsTraining() {
        Double sizeCollDouble = arrayList.size() * 0.2;
        Random rand = new Random();
        arrayListTraining = new ArrayList<>();
        for (int i = 0; i < sizeCollDouble.intValue(); i++) {
            DataToTask3Model dataToTask3Model = arrayList.get(rand.nextInt(arrayList.size()));
            arrayListTraining.add(dataToTask3Model);
            arrayList.remove(dataToTask3Model);
        }
    }

    public void createArrayWithRecordsTest() {
        Random rand = new Random();
        arrayListTest = new ArrayList<>();
        int arraySize = arrayList.size();
        for (int i = 0; i < arraySize; i++) {
            DataToTask3Model dataToTask3Model = arrayList.get(rand.nextInt(arrayList.size()));
            arrayListTest.add(dataToTask3Model);
            arrayList.remove(dataToTask3Model);
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private ArrayList<DataToTask3Model> testArrayList;
    private ArrayList<DataToTask3Model> trainingArrayList;

    public ArrayList<FeatureModel> getTestCollection() {
        ArrayList<FeatureModel> testCollection = new ArrayList<>();
        testArrayList = new ArrayList<>();
        Random rand = new Random();
        Double sizeCollDouble = allCollections.size() * 0.8;
        for (int i = 0; i < sizeCollDouble.intValue(); i++) {
            FeatureModel randomElement = allCollections.get(rand.nextInt(allCollections.size()));
            DataToTask3Model m = new DataToTask3Model();
            double[][] matrix = randomElement.getFeatureMatrix();

            for (int j = 0; j < matrix.length; j++) {
                m.setFeatureName(randomElement.getFeatureName());
                m.setFeatureList(matrix[j]);
                testArrayList.add(m);
            }

            testCollection.add(randomElement);
            allCollections.remove(randomElement);
        }
        return testCollection;
    }

    public ArrayList<FeatureModel> getTrainingCollection() {
        ArrayList<FeatureModel> trainingCollection = new ArrayList<>();
        trainingArrayList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            FeatureModel randomElement = allCollections.get(rand.nextInt(allCollections.size()));
            DataToTask3Model m = new DataToTask3Model();
            double[][] matrix1 = randomElement.getFeatureMatrix();

            for (int j = 0; j < matrix1.length; j++) {
                m.setFeatureName(randomElement.getFeatureName());
                m.setFeatureList(matrix1[j]);
                trainingArrayList.add(m);
            }
            trainingCollection.add(randomElement);
            allCollections.remove(randomElement);
        }
        return trainingCollection;
    }

    public ArrayList<DataToTask3Model> getTestArrayList() {
        return testArrayList;
    }

    public ArrayList<DataToTask3Model> getTrainingArrayList() {
        return trainingArrayList;
    }
}
