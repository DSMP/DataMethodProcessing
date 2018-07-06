package com.company;

import com.company.Selectors.SFS;
import com.company.classifiers.CrossValidationAlgorithm;
import com.company.classifiers.NN;
import com.company.service.CovariantMatrixService;
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
        fisherMethod.testFisher(fileReaderService.getFeatureModelArrayList().get(0).getFeatureMatrix(),
                fileReaderService.getFeatureModelArrayList().get(44).getFeatureMatrix(), 64, 21);

        System.out.println("++++++++Zadanie 2+++++++++++++++++");
        SFS sfs = new SFS();
        double[][] lol = sfs.calculateSFS(3);

//        for (int i = 0; i < lol.length; i++) {
//            System.out.print("" + i + " => ");
//            for (int j = 0; j < lol[0].length; j++) {
//                System.out.print(lol[i][j] + " ");
//            }
//            System.out.println();
//        }

        CreateCollections createCollections = new CreateCollections();
        createCollections.getCollection(fileReaderService.getFeatureModelArrayList());
        createCollections.createArrayWithRecords();

        nn.testClassNN(fileReaderService.getFeatureModelArrayList());


        System.out.println("++++++++Zadanie 4+++++++++++++++++");
        CrossValidationAlgorithm crossValidationAlgorithm = new CrossValidationAlgorithm(
                createCollections.getArrayList(), 10);

        crossValidationAlgorithm.divideArrayToParts();
        crossValidationAlgorithm.qualify("NN");




//        CovariantMatrixService a = new CovariantMatrixService();
//        double[][] qwe = new double[][]{{1,-1},{1,0},{2,-1},{1,-1}};
//        for (int i = 0; i < qwe.length; i++) {
//            for (int j = 0; j < qwe[0].length; j++) {
//                System.out.print(qwe[i][j] + " ");
//            }
//            System.out.println();
//        }
//        double[][] hmmm = a.calcCovMatrix(qwe);
//        double res = a.matrixDeterminant(hmmm);
    }
}
