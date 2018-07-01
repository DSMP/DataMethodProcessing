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

    public double[] calculateSFS(int featureCount)
    {
        splitClasses();
        MatrixClass matrixAcer = generateMatrix(dataAcer);
        MatrixClass matrixQuercus = generateMatrix(dataQuercus);
        double[] fisherDataCurrent = new double[0];

        for (int i = 0; i < featureCount; i++) {
            double[] avgRowsMatrixAcer = fisherMethod.calculateAvgVector(matrixAcer.getMatrix(),64);
            double[] avgRowsMatrixQuercus = fisherMethod.calculateAvgVector(matrixQuercus.getMatrix(),64);
            fisherDataCurrent = new double[64];
            for (int j = 0; j < 64; j++) {
                fisherDataCurrent[j] = fisherMethod.calculateFisher(avgRowsMatrixAcer[j], avgRowsMatrixQuercus[j], fisherMethod.calculateS(matrixAcer.getMatrix()[j],avgRowsMatrixAcer[j]), fisherMethod.calculateS(matrixQuercus.getMatrix()[j],avgRowsMatrixQuercus[j]));
            }
        }
        fisherDataCurrent[0] = bestResult(fisherDataCurrent);
        return fisherDataCurrent;
    }

    private double bestResult(double[] column)
    {
        double bestResult = -1;
        for (double result :column) {
            if (result > bestResult)
            {
                bestResult = result;
            }
        }
        return bestResult;
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
