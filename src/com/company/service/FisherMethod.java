package com.company.service;

import java.util.ArrayList;
import java.util.Collections;

public class FisherMethod {
    // policz srednią z wektoru
    public double[] calculateVector(double[][] matrix, int numberOfFeatures) {
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

    public double calculateS(double[] column, double avgOfColumn) {
        double result = 0;
        for (double aColumn : column) {
            result += Math.pow(aColumn - avgOfColumn, 2);
        }
        return Math.sqrt(result / column.length);
    }

    //calculate Fisher, one feature
    public double calculateFisher(double avarageA, double avarageB, double sigmaA, double sigmaB) {
        return Math.abs(avarageA - avarageB) / sigmaA + sigmaB;
    }

    private ArrayList<Integer> selection(ArrayList<Double> fisherValue, int howManyTheBestResults) {
        ArrayList<Integer> i = new ArrayList<>();
        for (int j = 0; j < howManyTheBestResults; j++) {
            int size = fisherValue.size();
            for (int p = 0; p < size; p++) {
                size = fisherValue.size();
                if (fisherValue.get(p).equals(Collections.max(fisherValue))) {
                    i.add(fisherValue.indexOf(fisherValue.get(p)));
                    fisherValue.set(p, 0.0);
                    break;
                }
            }
        }
        return i;
    }

    public static double[] getColumn(double[][] array, int index) {
        double[] column = new double[array.length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

    public void testFisher(double[][] matrixA, double[][] matrixB, int numberOfFeatures, int howManyTheBestResults) {
        double[] vectorA = calculateVector(matrixA, numberOfFeatures);
        double[] vectorB = calculateVector(matrixB, numberOfFeatures);
        ArrayList<Double> sForMatrixA = new ArrayList<>();
        ArrayList<Double> sForMatrixB = new ArrayList<>();
        ArrayList<Double> fisherValues = new ArrayList<>();

        System.out.println("+++++++++++Zadanie 1+++++++++++");
        System.out.println("Liczba wybranych cech: " + howManyTheBestResults);
        for (int i = 0; i < numberOfFeatures; i++) {
            for (int j = 0; j < 16; j++) {
                double[] m = getColumn(matrixA, i);
                sForMatrixA.add(calculateS(m, vectorA[i]));
            }
        }
        System.out.println();
        for (int i = 0; i < numberOfFeatures; i++) {
            for (int j = 0; j < 16; j++) {
                double[] m = getColumn(matrixB, i);
                sForMatrixB.add(calculateS(m, vectorB[i]));
            }
        }
        System.out.println("++Oblicz Fishera++");

        for (int i = 0; i < numberOfFeatures; i++) {
            //System.out.println("F" + i + ": " + calculateFisher(vectorA[i], vectorB[i], sForMatrixA.get(i), sForMatrixB.get(i)));
            fisherValues.add(calculateFisher(vectorA[i], vectorB[i], sForMatrixA.get(i), sForMatrixB.get(i)));
        }
        System.out.println();

        ArrayList<Double> fisherValuesClone = new ArrayList<>(fisherValues);
        ArrayList<Integer> theBestResults = new ArrayList<>(selection(fisherValues, howManyTheBestResults));
        fisherValues.clear();

        for (Integer theBestResult : theBestResults) {
            fisherValues.add(fisherValuesClone.get(theBestResult));
        }

        Collections.sort(fisherValues);
        Collections.reverse(fisherValues);

        for (int i = 0; i < fisherValues.size(); i++) {
            System.out.println((i + 1) + " - wartość: " + fisherValues.get(i));
        }

    }
}
