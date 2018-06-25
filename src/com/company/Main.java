package com.company;

<<<<<<< HEAD
import com.company.Selectors.SFS;
=======
import com.company.classifiers.NN;
import com.company.service.CreateCollections;
>>>>>>> b0fb468957dd2389fd8ae7605dd81fd757ae421d
import com.company.service.FileReaderService;
import com.company.service.FisherMethod;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
<<<<<<< HEAD
//        FileReaderService fileReaderService = new FileReaderService();
//        FisherMethod fisherMethod = new FisherMethod();
//        try {
//            fileReaderService.readFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        fisherMethod.testFisher(fileReaderService.getRecordDataArrayList().get(1).getFeatureMatrix(), fileReaderService.getRecordDataArrayList().get(44).getFeatureMatrix());
        SFS sfs = new SFS();

=======
        FileReaderService fileReaderService = new FileReaderService();
        FisherMethod fisherMethod = new FisherMethod();
        NN nn = new NN();
        try {
            fileReaderService.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fileReaderService.getRecordDataArrayList().get(1).getFeatureMatrix().length; i++) {
            for (int j = 0; j < fileReaderService.getRecordDataArrayList().get(1).getFeatureMatrix()[i].length; j++) {
                System.out.print(fileReaderService.getRecordDataArrayList().get(1).getFeatureMatrix()[i][j]+" ");
            }
            System.out.println();
        }
//        nn.testNN(fileReaderService.getRecordDataArrayList());
        //fisherMethod.testFisher(fileReaderService.getRecordDataArrayList().get(1).getFeatureMatrix(), fileReaderService.getRecordDataArrayList().get(44).getFeatureMatrix());
>>>>>>> b0fb468957dd2389fd8ae7605dd81fd757ae421d
    }
}
