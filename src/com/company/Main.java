package com.company;

import com.company.service.FileReaderService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        FileReaderService fileReaderService = new FileReaderService();
        try {
            fileReaderService.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        fisherMethod.testFisher(fileReaderService.getRecordDataArrayList().get(1).getFeatureMatrix(), fileReaderService.getRecordDataArrayList().get(44).getFeatureMatrix());
    }
}
