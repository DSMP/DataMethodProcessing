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

    public double[][] calculateSFS(int userCount)
    {
        splitClasses();
        double[][] matrixAcer = generateMatrix(dataAcer).getMatrix();
        double[][] matrixQuercus = generateMatrix(dataQuercus).getMatrix();
        double[][] bestFeaturesAcer = new double[matrixAcer.length][64];
        double[][] bestFeaturesQuerqos = new double[matrixQuercus.length][64];
        double[] fisherDataCurrent = new double[0];
        double[] avgRowsMatrixAcer = fisherMethod.calcAvgVector(matrixAcer,64);
        double[] avgRowsMatrixQuercus = fisherMethod.calcAvgVector(matrixQuercus,64);
        int theBestFeaturesCount = 0;

        fisherDataCurrent = new double[64];

        for (int j = 0; j < 64; j++) {
            fisherDataCurrent[j] = fisherMethod.calcFisher(avgRowsMatrixAcer[j], avgRowsMatrixQuercus[j], fisherMethod.calculateS(matrixAcer[j],avgRowsMatrixAcer[j]), fisherMethod.calculateS(matrixQuercus[j],avgRowsMatrixQuercus[j]));
        }
        int theBestFeature = bestResult(fisherDataCurrent);
        bestFeaturesAcer = addfeatureToBestFeatures(matrixAcer, bestFeaturesAcer, theBestFeature, theBestFeaturesCount);
        bestFeaturesQuerqos = addfeatureToBestFeatures(matrixQuercus, bestFeaturesQuerqos, theBestFeature, theBestFeaturesCount);
        theBestFeaturesCount++;
        matrixAcer = rmFeature(matrixAcer, theBestFeature);
        matrixQuercus = rmFeature(matrixQuercus, theBestFeature);

        for (int i = 1; i < userCount; i++) {
            fisherDataCurrent[63-i] = -1;
            for (int j = 0; j < matrixAcer[0].length; j++) {
                bestFeaturesAcer = addfeatureToBestFeatures(matrixAcer, bestFeaturesAcer, j, theBestFeaturesCount);
                bestFeaturesQuerqos = addfeatureToBestFeatures(matrixQuercus, bestFeaturesQuerqos, j, theBestFeaturesCount);
                fisherDataCurrent[j] = fisherMethod.calcAutoFisher(makeRealMatrix(bestFeaturesAcer,i+1),makeRealMatrix(bestFeaturesQuerqos, i+1)
                        ,makeRealMatrix(bestFeaturesAcer,i+1),makeRealMatrix(bestFeaturesQuerqos, i+1));
                bestFeaturesAcer = rmFeature(bestFeaturesAcer, i);
                bestFeaturesQuerqos = rmFeature(bestFeaturesQuerqos, i);
            }
            theBestFeature = bestResult(fisherDataCurrent);
            bestFeaturesAcer = addfeatureToBestFeatures(matrixAcer, bestFeaturesAcer, theBestFeature, theBestFeaturesCount);
            bestFeaturesQuerqos = addfeatureToBestFeatures(matrixQuercus, bestFeaturesQuerqos, theBestFeature, theBestFeaturesCount);
            theBestFeaturesCount++;
            matrixAcer = rmFeature(matrixAcer, theBestFeature);
            matrixQuercus = rmFeature(matrixQuercus, theBestFeature);

        }
        return matrixAddAnotherMatrix(bestFeaturesAcer,bestFeaturesQuerqos);
    }

    private double[][] matrixAddAnotherMatrix(double[][] matrixA, double[][] matrixB)
    {
        double[][] matrixResult = new double[matrixA.length+matrixB.length][matrixA[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                matrixResult[i][j] = matrixA[i][j];
            }
        }
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                matrixResult[matrixA.length+i][j] = matrixB[i][j];
            }
        }
        return matrixResult;
    }

    private double[][] addfeatureToBestFeatures(double[][] matrixFeatures, double[][] bestFeatures, int theBestFeature, int theBestFeaturesCount) {
        double[][] matrixResultFeatures = new double[matrixFeatures.length][theBestFeaturesCount+1];
        for (int i = 0; i < bestFeatures.length; i++) {
            for (int j = 0; j < theBestFeaturesCount; j++) {
                matrixResultFeatures[i][j] = bestFeatures[i][j];
            }
        }
        for (int i = 0; i < bestFeatures.length; i++) {
            matrixResultFeatures[i][theBestFeaturesCount] = matrixFeatures[i][theBestFeature];
        }
        return matrixResultFeatures;
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
            if (Double.isInfinite(result))
            {
                continue;
            }
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

    private double[][] makeRealMatrix(final double[][] matrix, int countColumns)
    {
        double[][] matrixResult = new double[matrix.length][countColumns];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < countColumns; j++) {
                matrixResult[i][j] = matrix[i][j];
            }
        }
        return matrixResult;
    }

}