package com.company.service;

import java.util.ArrayList;
import java.util.Collections;

public class FisherMethod {
    public double[] calculateVector(double[][] matrix) {
        double[] vector = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            double result = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                result += matrix[i][j];
            }
            vector[i] = result / matrix[i].length;
        }
        return vector;
    }

    public ArrayList<Double> calculateS(double[][] matrix, double[] vectorValue) {
        ArrayList<Double> listOfS = new ArrayList<Double>();
        for (int i = 0; i < matrix.length; i++) {
            double result = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                result += Math.pow(matrix[i][j] - vectorValue[i], 2);
            }
            result = Math.sqrt(result / matrix[i].length);
            listOfS.add(result);
        }
        return listOfS;
    }

    public double calculateFisher(double vectorA, double vectorB, double valueSA, double valueSB) {
        return Math.abs(vectorA - vectorB) / valueSA + valueSB;
    }

    public int selection(ArrayList<Double> fisherValue) {
        int i = -1;
        for (Double v: fisherValue) {
            if(v.equals(Collections.max(fisherValue))) {
                i = fisherValue.indexOf(v);
            }
        }
        return i;
    }
}
