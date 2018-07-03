package com.company.service;

public class CovariantMatrixService {
    FisherMethod fisherMethod;

    public CovariantMatrixService() {
        this.fisherMethod = new FisherMethod();
    }

    public double[][] calcCovMatrix(final double[][] matrix)
    {
        double[] vectorAvg = fisherMethod.calculateAvgVector(matrix,matrix[0].length);
        double[][] matrixAvg = makeAvgVectorToMatrix(vectorAvg,matrix.length);
        double[][] matrixDiff = calcDiffMatrix(matrix, matrixAvg);
        double[][] matrixTrans = makeTransMatrix(matrix);
        double[][] matrixMul = multiply(matrixDiff,matrixTrans);
        matrixMul = multiply(matrixMul,matrix[0].length);
        return matrixMul;
    }
    private double[][] multiply(double[][] matrix, double scalar)
    {
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;
    }

    private double[][] multiply(double[][] a, double[][] b) {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    private double[][] makeTransMatrix(final double[][] matrixSource)
    {
        double[][] matrixResult = new double[matrixSource[0].length][matrixSource.length];
        for (int i = 0; i < matrixSource.length; i++) {
            for (int j = 0; j < matrixSource[0].length; j++) {
                matrixResult[j][i] = matrixSource[i][j];
            }
        }
        return matrixResult;
    }

    private double[][] makeAvgVectorToMatrix(final double[] avgVector, final int sizeY)
    {
        double[][] matrixResult = new double[sizeY][avgVector.length];
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < avgVector.length; j++) {
                matrixResult[i][j] = avgVector[j];
            }
        }
        return matrixResult;
    }

    private double[][] calcDiffMatrix(final double[][] matrixF, final double[][] matrixAvg)
    {
        double[][] matrixResult= new double[matrixF.length][matrixF[0].length];
        for (int i = 0; i < matrixF.length - 1; i++) {
            for (int j = 0; j < matrixF[0].length - 1; j++) {
                matrixResult[i][j] = matrixF[i][j] - matrixAvg[i][j];
            }
        }
        return matrixResult;
    }
}
