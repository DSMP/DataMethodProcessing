package com.company;

import com.company.Selectors.SFS;
import com.company.classifiers.NN;
import com.company.service.CovariantMatrixService;
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
//        fisherMethod.testFisher(fileReaderService.getFeatureModelArrayList().get(0).getFeatureMatrix(), fileReaderService.getFeatureModelArrayList().get(44).getFeatureMatrix(), 64, 21);
//
//        nn.testClassNN(fileReaderService.getFeatureModelArrayList());

        //SFS sfs = new SFS();
        //double[] lol = sfs.calculateSFS(1);
        CovariantMatrixService a = new CovariantMatrixService();
        double[][] hmmm = a.calcCovMatrix(new double[][]{{1,1},{2,1},{-1,0},{-1,-1}});
    }
}
