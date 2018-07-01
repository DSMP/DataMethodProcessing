package com.company.service;

public class CovariantMatrixService {
    FisherMethod fisherMethod;

    public CovariantMatrixService() {
        this.fisherMethod = new FisherMethod();
    }
    //todo: przetestuj
    public double[][] calcCovMatrix(final double[][] matrix)
    {
        double[] vectorAvg = fisherMethod.calculateAvgVector(matrix,matrix[0].length);
        double[][] matrixAvg = makeAvgVectorToMatrix(vectorAvg,matrix[0].length);
        double[][] matrixDiff = calcDiffMatrix(matrix, matrixAvg);
        double[][] matrixTrans = makeTransMatrix(matrix);
        double[][] matrixResult = new double[matrix.length][matrix[0].length];
        int newMatrixSize = Math.min(matrix.length,matrix[0].length);
        int matrixLongerSize = Math.max(matrix.length,matrix[0].length);
        for (int i = 0; i < newMatrixSize - 1; i++) {
            for (int j = 0; j < newMatrixSize - 1; j++) {
                double cell = 0;
                for (int k = 0; k < matrixLongerSize - 1; k++) {
                    cell += matrix[i][k] * matrix[k][i];
                }
                matrixResult[i][j] = cell;
            }
        }
        return matrixResult;
    }

    private double[][] makeTransMatrix(final double[][] matrixSource)
    {
        double[][] matrixResult = new double[matrixSource[0].length][matrixSource.length];
        for (int i = 0; i < matrixSource.length-1; i++) {
            for (int j = 0; j < matrixSource[0].length-1; j++) {
                matrixResult[j][i] = matrixSource[i][j];
            }
        }
        return matrixResult;
    }

    private double[][] makeAvgVectorToMatrix(final double[] avgVector, final int sizeY)
    {
        double[][] matrixResult = new double[avgVector.length][sizeY];
        for (int i = 0; i < avgVector.length-1; i++) {
            for (int j = 0; j < sizeY-1; j++) {
                matrixResult[i][j] = avgVector[j];
            }
        }
        return matrixResult;
    }

    private double[][] calcDiffMatrix(final double[][] matrixF, final double[][] matrixAvg)
    {
        double[][] matrixResult= new double[matrixF.length][];
        for (int i = 0; i < matrixF.length; i++) {
            for (int j = 0; j < matrixF[0].length; j++) {
                matrixResult[i][j] = matrixF[i][j] - matrixAvg[i][j];
            }
        }
        return matrixResult;
    }
}
