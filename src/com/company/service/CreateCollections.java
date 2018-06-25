package com.company.service;

import com.company.model.RecordData;

import java.util.ArrayList;
import java.util.Random;

public class CreateCollections {
    private ArrayList<RecordData> allCollections;

    public void getCollection(ArrayList<RecordData> allCollections) {
        this.allCollections = allCollections;
    }

    public ArrayList<RecordData> getTestCollection() {
        ArrayList<RecordData> testCollection = new ArrayList<>();
        Random rand = new Random();
        Double sizeCollDouble = allCollections.size()*0.8;
        for(int i = 0; i < sizeCollDouble.intValue(); i++) {
            RecordData randomElement = allCollections.get(rand.nextInt(allCollections.size()));
            allCollections.remove(randomElement);
            testCollection.add(randomElement);
        }
        return testCollection;
    }

    public ArrayList<RecordData> getTrainingCollection() {
        ArrayList<RecordData> trainingCollection = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            RecordData randomElement = allCollections.get(rand.nextInt(allCollections.size()));
            allCollections.remove(randomElement);
            trainingCollection.add(randomElement);
        }
        return trainingCollection;
    }
}
