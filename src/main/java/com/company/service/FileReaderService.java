package com.company.service;

import com.company.model.FeatureModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReaderService {
    private ArrayList<FeatureModel> featureModelArrayList = new ArrayList<FeatureModel>();

    public void readFile() throws IOException {
        String lastRowName = null;
        boolean firstLine = true;
        BufferedReader br = new BufferedReader(new FileReader("src\\Maple_Oak.txt"));
        try {
            String line = null;
            int j = 0;
            double[][] matrix = new double[16][64];
            while ((line = br.readLine()) != null) {
                String[] recordArray = line.split(",");
                if (firstLine) {
                    firstLine = false;
                    for (int i = 1; i < recordArray.length; i++) {
                        matrix[j][i - 1] = Double.valueOf(recordArray[i]);
                    }
                    lastRowName = recordArray[0];
                    j++;
                } else if (recordArray[0].equals(lastRowName)) {
                    for (int i = 1; i < recordArray.length; i++) {
                        matrix[j][i - 1] = Double.valueOf(recordArray[i]);
                    }
                    lastRowName = recordArray[0];
                    j++;
                } else {
                    firstLine = true;
                    FeatureModel rd = createFeatureObject(matrix, lastRowName);
                    featureModelArrayList.add(rd);
                    matrix = new double[16][64];
                    j = 0;
                    for (int i = 1; i < recordArray.length; i++) {
                        matrix[j][i - 1] = Double.valueOf(recordArray[i]);
                    }
                    lastRowName = recordArray[0];
                    j++;
                }
            }
        } finally {
            br.close();
        }
    }

    private FeatureModel createFeatureObject(double[][] featureMatrix, String objectName) {
        return new FeatureModel(objectName, featureMatrix);
    }

    public ArrayList<FeatureModel> getFeatureModelArrayList() {
        return featureModelArrayList;
    }
}
