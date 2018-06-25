package com.company.service;

import java.util.ArrayList;
import java.util.Collections;

public class FisherMethod {
    public double[] calculateVector(double[][] matrix) {
        double[] vector = new double[matrix[0].length];
        for (int i = 0; i < matrix[0].length - 1; i++) {
            double result = 0;
            for (int j = 0; j < matrix.length; j++) {
                result += matrix[j][i];
            }
            vector[i] = result / matrix[0].length;
        }
        return vector;
    }

    public ArrayList<Double> calculateS(double[][] matrix, double[] vectorValue) {
        ArrayList<Double> listOfS = new ArrayList<Double>();
        for (int i = 0; i < matrix[0].length; i++) {
            double result = 0;
            for (int j = 0; j < matrix.length; j++) {
                result += Math.pow(matrix[j][i] - vectorValue[j], 2);
            }
            result = Math.sqrt(result / matrix[0].length);
            listOfS.add(result);
        }
        return listOfS;
    }

    public double calculateFisher(double vectorA, double vectorB, double valueSA, double valueSB) {
        return Math.abs(vectorA - vectorB) / valueSA + valueSB;
    }

    public int selection(ArrayList<Double> fisherValue) {
        int i = 0;
        System.out.println(Collections.max(fisherValue));
        for (Double v : fisherValue) {
            if(!v.isNaN()) {
                if (v.equals(Collections.max(fisherValue))) {
                    i = fisherValue.indexOf(v);
                }
            }
        }
        return i;
    }

    public void testFisher(double[][] matrixA,  double[][] matrixB) {
        double[] vectorA = calculateVector(matrixA);
        double[] vectorB = calculateVector(matrixB);
        ArrayList<Double> sListA =  calculateS(matrixA, vectorA);
        System.out.println(sListA.size());
        ArrayList<Double> sListB =  calculateS(matrixB, vectorB);
        System.out.println(sListB.size());
        ArrayList<Double> fisherValue = new ArrayList<Double>();
        for(int i = 0 ; i < sListB.size(); i++) {
            fisherValue.add(calculateFisher(sListA.get(i), sListB.get(i), vectorA[i], vectorB[i]));
//            System.out.println(calculateFisher(sListA.get(i), sListB.get(i), vectorA[i], vectorB[i]));
        }
        fisherValue.get(selection(fisherValue));
//        System.out.println(fisherValue.get(selection(fisherValue)));
    }
}
