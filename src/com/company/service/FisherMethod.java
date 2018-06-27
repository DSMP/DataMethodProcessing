package com.company.service;

import java.util.ArrayList;
import java.util.Collections;

public class FisherMethod {
    private double[] calculateVector(double[][] matrix, int numberOfFeatures) {
        double[] vector = new double[numberOfFeatures];
        for (int i = 0; i < numberOfFeatures; i++) {
            double result = 0;
            for (double[] aMatrix : matrix) {
                result += aMatrix[i];
            }
            vector[i] = result / numberOfFeatures;
        }
        return vector;
    }

    private double calculateS(double[] column, double vectorValue) {
        double result = 0;
        for (double aColumn : column) {
            result += Math.pow(aColumn - vectorValue, 2);
        }
        return Math.sqrt(result / column.length);
    }

    private double calculateFisher(double vectorA, double vectorB, double valueSA, double valueSB) {
        return Math.abs(vectorA - vectorB) / valueSA + valueSB;
    }

    private int selection(ArrayList<Double> fisherValue) {
        int i = 0;
        for (Double v : fisherValue) {
            if (!v.isNaN()) {
                if (v.equals(Collections.max(fisherValue))) {
                    i = fisherValue.indexOf(v);
                }
            }
        }
        return i;
    }

    public void testFisher(double[][] matrixA, double[][] matrixB, int numberOfFeatures) {
        double[] vectorA = calculateVector(matrixA, numberOfFeatures);
        double[] vectorB = calculateVector(matrixB, numberOfFeatures);
        ArrayList<Double> sForMatrixA = new ArrayList<>();
        ArrayList<Double> sForMatrixB = new ArrayList<>();
        ArrayList<Double> fisherValues = new ArrayList<>();
        System.out.println("+++++++++++Zadanie 1+++++++++++");
        System.out.println("Liczba wybranych cech: " + numberOfFeatures);
        System.out.println("\n++Oblicz wartosci S++");
        for (int i = 0; i < numberOfFeatures; i++) {
            System.out.println("Sa" + i + ": " + calculateS(matrixA[numberOfFeatures], vectorA[i]));
            sForMatrixA.add(calculateS(matrixA[numberOfFeatures], vectorA[i]));
        }
        System.out.println();
        for (int i = 0; i < numberOfFeatures; i++) {
            System.out.println("Sb" + i + ": " + calculateS(matrixB[numberOfFeatures], vectorB[i]));
            sForMatrixB.add(calculateS(matrixB[numberOfFeatures], vectorB[i]));
        }

        System.out.println("\n++Oblicz Fishera++");

        for (int i = 0; i < numberOfFeatures; i++) {
            System.out.println("F" + i + ": " + calculateFisher(vectorA[i], vectorB[i], sForMatrixA.get(i), sForMatrixB.get(i)));
            fisherValues.add(calculateFisher(vectorA[i], vectorB[i], sForMatrixA.get(i), sForMatrixB.get(i)));
        }

        System.out.println("\nNajwiększa wartość: " + fisherValues.get(selection(fisherValues)));
        System.out.println("Indeks: " + selection(fisherValues));
    }
}
