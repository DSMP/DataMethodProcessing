package com.company.model;

public class MatrixClass {
    private double[][] matrix;
    private int lengthX;
    private int lengthY;

    public MatrixClass(double[][] matrix, int lengthX, int lengthY) {
        this.matrix = matrix;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getLengthX() {
        return lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }
}
