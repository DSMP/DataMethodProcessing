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

    public double[] calculateSFS(int userCount)
    {
        splitClasses();
        double[][] matrixAcer = generateMatrix(dataAcer).getMatrix();
        double[][] matrixQuercus = generateMatrix(dataQuercus).getMatrix();
        double[][] bestFeaturesAcer = new double[matrixAcer.length][64];
        double[][] bestFeaturesQuerqos = new double[matrixQuercus.length][64];
        double[] fisherDataCurrent = new double[0];
        double[] avgRowsMatrixAcer = fisherMethod.calculateAvgVector(matrixAcer,64);
        double[] avgRowsMatrixQuercus = fisherMethod.calculateAvgVector(matrixQuercus,64);

        fisherDataCurrent = new double[64];

        for (int j = 0; j < 64; j++) {
            fisherDataCurrent[j] = fisherMethod.calculateFisher(avgRowsMatrixAcer[j], avgRowsMatrixQuercus[j], fisherMethod.calculateS(matrixAcer[j],avgRowsMatrixAcer[j]), fisherMethod.calculateS(matrixQuercus[j],avgRowsMatrixQuercus[j]));
        }
        int theBestFeature = bestResult(fisherDataCurrent);
        for (int i = 0; i < bestFeaturesAcer[0].length; i++) {
            bestFeaturesAcer[i][0] = matrixAcer[i][theBestFeature];
            bestFeaturesQuerqos[i][0] = matrixQuercus[i][theBestFeature];
        }
        matrixAcer = rmFeature(matrixAcer, theBestFeature);
        matrixQuercus = rmFeature(matrixQuercus, theBestFeature);
                
        return fisherDataCurrent;
    }

    private double[][] rmFeature(double[][] matrix, int index) {
        double[][] matrixresult = new double[matrix.length][matrix[0].length-1];
        for (int i = 0; i < matrixresult.length; i++) {
            for (int j = 0; j < matrixresult[0].length; j++) {
                if (j == index) continue;
                matrixresult[i][j] = matrix[i][j];
            }
        }
        return matrixresult;
    }

    private double[] getAvgVector(double[] vectorAvg, int lengthVector, int startPos) {
        double[] result = new double[lengthVector];
        for (int i = startPos; i < lengthVector; i++) {
            result[i] = vectorAvg[i];
        }
        return result;
    }


    private int bestResult(double[] column)
    {
        double bestResult = -1;
        int bestPoz = 0, i = 0;
        for (double result :column) {
            if (result > bestResult)
            {
                bestResult = result;
                bestPoz = i;
            }
            i++;
        }
        return bestPoz;
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
