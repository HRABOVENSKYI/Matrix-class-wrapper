package com.epam.test.automation.java.practice9;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Matrix {

    public static final String INCOMPATIBLE_MATRIX_SIZES = "Incompatible matrix sizes";

    private final double[][] aMatrix;

    private static final Logger logger = Logger.getLogger("MyLogger");

    /**
     * Implement a constructor that creates an empty matrix with a given number of rows
     * columns (all values in matrix equal 0.0)
     *
     * @param row    number of rows
     * @param column number of columns
     * @return Returns a new instance of the matrix with the specified parameters
     */
    public Matrix(int row, int column) {
        this.aMatrix = new double[row][column];
    }

    /**
     * Implement a constructor that creating of matrix based on existing two-dimensional array.
     *
     * @param twoDimensionalArray existing two-dimensional array
     * @return Returns a new instance of the matrix based on existing two-dimensional array
     * @throws MatrixException if the incoming array with zero number of rows returns the message "Array passed with zero number of rows",
     *                         if the incoming array with zero number of columns returns the message "Array passed with zero number of columns"
     */
    public Matrix(double[][] twoDimensionalArray) throws MatrixException {
        if (twoDimensionalArray.length == 0) {
            throw new MatrixException("Array passed with zero number of rows");
        } else if (twoDimensionalArray[0].length == 0) {
            throw new MatrixException("Array passed with zero number of columns");
        } else {
            this.aMatrix = twoDimensionalArray;
        }
    }

    /**
     * @return Returns the number of rows in a matrix
     */
    public final int rows() {
        return aMatrix.length;
    }

    /**
     * @return Returns the number of columns in a matrix
     */
    public final int columns() {
        return aMatrix[0].length;
    }

    /**
     * Receiving of standard two-dimensional array out of matrix.
     *
     * @return Standard two-dimensional array
     */
    public double[][] twoDimensionalArrayOutOfMatrix() {
        return aMatrix;
    }

    /**
     * Reading of elements via predetermined correct index
     *
     * @param row    number of rows
     * @param column number of columns
     * @return Returns the value of a matrix element <code>[row,column]</code>
     * @throws MatrixException if index incorrect, returns message "Incompatible matrix sizes"
     */
    public double getValue(int row, int column) throws MatrixException {
        if (row >= this.rows() || column >= this.columns()) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES);
        } else {
            return aMatrix[row][column];
        }
    }

    /**
     * Recording value <code>newValue</code> of elements via predetermined correct index <code>[row,column]</code>     *
     *
     * @param row      number of rows
     * @param column   number of columns
     * @param newValue new value of a matrix element
     * @throws MatrixException if index incorrect, returns message "Incompatible matrix sizes"
     */
    public void setValue(int row, int column, double newValue) throws MatrixException {
        if (row >= this.rows() || column >= this.columns()) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES);
        } else {
            aMatrix[row][column] = newValue;
        }
    }

    /**
     * Method of matrix's addition  <code>matrix</code>.
     * Result in the original matrix
     *
     * @param matrix matrix corresponding to the second term
     * @return Returns a new resulting matrix
     * @throws MatrixException if incompatible matrix sizes, returns message "Incompatible matrix sizes"
     */
    public Matrix addition(Matrix matrix) throws MatrixException {
        if (this.rows() != matrix.rows() || this.columns() != matrix.columns()) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES);
        } else {
            for (int i = 0; i < this.rows(); i++) {
                for (int j = 0; j < this.columns(); j++) {
                    this.setValue(i, j, this.getValue(i, j) + matrix.getValue(i, j));
                }
            }
            return this;
        }
    }

    /**
     * Method of matrix's deduction <code>matrix</code> from original.
     * Result in the original matrix
     *
     * @param matrix matrix corresponding to the subtracted
     * @return Returns a new resulting matrix
     * @throws MatrixException if incompatible matrix sizes, returns message "Incompatible matrix sizes"
     */
    public Matrix subtraction(final Matrix matrix) throws MatrixException {
        if (this.rows() != matrix.rows() || this.columns() != matrix.columns()) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES);
        } else {
            for (int i = 0; i < this.rows(); i++) {
                for (int j = 0; j < this.columns(); j++) {
                    this.setValue(i, j, this.getValue(i, j) - matrix.getValue(i, j));
                }
            }
            return this;
        }
    }

    /**
     * Method of matrix's multiplication <code>matrix</code>
     * Result in the original matrix
     *
     * @param matrix matrix corresponding to the second factor
     * @return Returns a new resulting matrix
     * @throws MatrixException if incompatible matrix sizes, returns message "Incompatible matrix sizes"
     */
    public Matrix multiplication(final Matrix matrix) throws MatrixException {
        if (this.rows() != matrix.columns() || this.columns() != matrix.rows()) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES);
        } else {
            Matrix resultMatrix = new Matrix(this.rows(), matrix.columns());

            for (int row = 0; row < resultMatrix.rows(); row++) {
                for (int col = 0; col < resultMatrix.columns(); col++) {
                    resultMatrix.setValue(row, col, multiplyMatricesCell(this, matrix, row, col));
                }
            }
            return resultMatrix;
        }
    }

    double multiplyMatricesCell(Matrix firstMatrix, Matrix secondMatrix, int row, int col) throws MatrixException {
        double cell = 0;
        for (int i = 0; i < secondMatrix.rows(); i++) {
            cell += firstMatrix.getValue(row, i) * secondMatrix.getValue(i, col);
        }
        return cell;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                try {
                    if (j != this.columns() - 1) {
                        builder.append(decimalFormat.format(getValue(i, j)) + " ");
                    } else {
                        builder.append(decimalFormat.format(getValue(i, j)));
                    }
                } catch (MatrixException e) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        boolean equals = true;
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.columns(); j++) {
                try {
                    if (matrix.getValue(i, j) != this.getValue(i, j)) {
                        equals = false;
                        break;
                    }
                } catch (MatrixException e) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(aMatrix);
    }
}