package com.company;

import com.company.service.FileReaderService;
import com.company.service.FisherMethod;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderService();
        FisherMethod fisherMethod = new FisherMethod();
        try {
            fileReaderService.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[][] matrixA = fileReaderService.getRecordDataArrayList().get(1).getFeatureMatrix();
        double[][] matrixB = fileReaderService.getRecordDataArrayList().get(44).getFeatureMatrix();
        double[] vectorA = fisherMethod.calculateVector(matrixA);
        double[] vectorB = fisherMethod.calculateVector(matrixB);
        ArrayList<Double> sListA =  fisherMethod.calculateS(matrixA, vectorA);
        ArrayList<Double> sListB =  fisherMethod.calculateS(matrixB, vectorB);
        ArrayList<Double> fisherValue = new ArrayList<>();
        for(int i = 0 ; i < sListB.size(); i++) {
            fisherValue.add(fisherMethod.calculateFisher(sListA.get(i), sListB.get(i), vectorA[i], vectorB[i]));
            System.out.println(fisherMethod.calculateFisher(sListA.get(i), sListB.get(i), vectorA[i], vectorB[i]));
        }
        System.out.println(fisherValue.get(fisherMethod.selection(fisherValue)));
    }
}
