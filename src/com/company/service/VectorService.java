package com.company.service;

public class VectorService {
    public double[] calcAvgVector(double[][] matrix, int numberOfFeatures) {
        double[] vector = new double[numberOfFeatures];
        for (int i = 0; i < numberOfFeatures; i++) {
            double result = 0;
            for (int j = 0; j < matrix.length; j++) {
                result += matrix[j][i];
            }
            vector[i] = result / matrix.length;
        }
        return vector;
    }
}
