package com.company.Selectors;

import com.company.model.FeatureModel;
import com.company.service.FileReaderService;

import java.io.IOException;
import java.util.ArrayList;

public class SFS {
    FileReaderService fileReaderService;
    ArrayList<FeatureModel> data;
    public SFS() {
        fileReaderService = new FileReaderService();
        try {
            fileReaderService.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = fileReaderService.getFeatureModelArrayList();
    }

    public void CalculateSFS(int featureCount)
    {
        for (int i = 0; i < featureCount; i++) {

        }
    }
}
