package com.company;

import com.company.Selectors.SFS;
import com.company.classifiers.NN;
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

        fisherMethod.testFisher(fileReaderService.getFeatureModelArrayList().get(1).getFeatureMatrix(), fileReaderService.getFeatureModelArrayList().get(44).getFeatureMatrix(), 7);
        nn.testClassNN(fileReaderService.getFeatureModelArrayList());

    }
}
