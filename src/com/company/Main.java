package com.company;

import com.company.classifiers.NN;
import com.company.service.CreateCollections;
import com.company.service.FileReaderService;
import com.company.service.FisherMethod;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderService();
        FisherMethod fisherMethod = new FisherMethod();
        NN nn = new NN();
        try {
            fileReaderService.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        nn.testNN(fileReaderService.getRecordDataArrayList());
        //fisherMethod.testFisher(fileReaderService.getRecordDataArrayList().get(1).getFeatureMatrix(), fileReaderService.getRecordDataArrayList().get(44).getFeatureMatrix());
    }
}
