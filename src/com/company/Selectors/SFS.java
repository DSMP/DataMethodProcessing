package com.company.Selectors;

import com.company.model.FeatureModel;
import com.company.model.MatrixClass;
import com.company.service.FileReaderService;
import com.company.service.FisherMethod;

import java.io.IOException;
import java.util.ArrayList;

public class SFS {
    FileReaderService fileReaderService;
    FisherMethod fisherMethod;
    ArrayList<FeatureModel> data;
    ArrayList<FeatureModel> dataAcer;
    ArrayList<FeatureModel> dataQuercus;

    public SFS() {
        fileReaderService = new FileReaderService();
        fisherMethod = new FisherMethod();
        dataAcer = new ArrayList<>();
        dataQuercus = new ArrayList<>();
        try {
            fileReaderService.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = fileReaderService.getFeatureModelArrayList();
    }

    public void calculateSFS(int featureCount)
    {
        splitClasses();
        MatrixClass MatrixAcer = generateMatrix(dataAcer);
        MatrixClass MatrixQuercus = generateMatrix(dataQuercus);

        for (int i = 0; i < featureCount; i++) {

        }
    }

    private void splitClasses()
    {
        data.forEach(featureModel -> {
            if(featureModel.getFeatureName().contains("Acer"))
            {
                dataAcer.add(featureModel);
            }
            else
            {
                dataQuercus.add(featureModel);
            }
        });
    }

    private MatrixClass generateMatrix(ArrayList<FeatureModel> features)
    {
        double[][] resultMatrixData = new double[16*features.size()][64];
        int ii = 0;
        for (FeatureModel featureModel: features) {
            for (int i = 0; i <= 16-1; i++,ii++) {
                for (int j = 0; j < 64-1; j++) {
                    resultMatrixData[ii][j] = featureModel.getFeatureMatrix()[i][j];
                }
            }
        }
        MatrixClass matrixClass = new MatrixClass(resultMatrixData, ii, resultMatrixData[0].length);
        return matrixClass;
    }
}
