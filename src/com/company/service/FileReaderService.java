package com.company.service;

import com.company.model.RecordData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReaderService {
    private ArrayList<RecordData> recordDataArrayList = new ArrayList<RecordData>();

    public void readFile() throws IOException {
        String lastRowName = null;
        boolean firstLine = true;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Artur\\Desktop\\SMPDconsole\\src\\Maple_Oak.txt"));
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
                    RecordData rd = createFeatureObject(matrix, lastRowName);
                    recordDataArrayList.add(rd);
                    j = 0;
                }
            }
        } finally {
            br.close();
        }
    }

    private RecordData createFeatureObject(double[][] featureMatrix, String objectName) {
        return new RecordData(objectName, featureMatrix);
    }

    public ArrayList<RecordData> getRecordDataArrayList() {
        return recordDataArrayList;
    }

    public ArrayList<String> getFeatureName() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < recordDataArrayList.size(); i++) {
            list.add(recordDataArrayList.get(i).getFeatureName());
        }
        return list;
    }
}
