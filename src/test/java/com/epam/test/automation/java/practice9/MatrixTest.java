package com.epam.test.automation.java.practice9;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class MatrixTest{

    private static Matrix matrix1;
    private static Matrix matrix2;
    private static Matrix matrix3;
    private static Matrix matrix31;
    private static Matrix matrix4;
    private static Matrix matrix5;
    private static Matrix matrix6;

    static {
        try {
            matrix1 = new Matrix(new double[][]{{2, 2.1, 2.2}, {3, 3.1, 3.2}, {4, 4.1, 4.2}});
            matrix2 = new Matrix(new double[][]{{3, 3.1, 3.2}, {4, 4.1, 4.2}, {5, 5.1, 5.2}});
            matrix3 = new Matrix(2, 3);
            matrix31 = new Matrix(3, 2);
            matrix4 = new Matrix(new double[][]{{4, 4.1, 4.2}, {5, 5.1, 5.2}, {6, 6.1, 6.2}});
            matrix5 = new Matrix(new double[][]{{5, 5.1, 5.2}, {6, 6.1, 6.2}, {7, 7.1, 7.2}});
            matrix6 = new Matrix(new double[][]{{9, 9.2, 9.4}, {11, 11.2, 11.4}, {13, 13.2, 13.4}});
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetValue() throws MatrixException {

        // matrix1
        Assert.assertEquals(matrix1.getValue(0, 0), 2);
        Assert.assertEquals(matrix1.getValue(0, 1), 2.1);
        Assert.assertEquals(matrix1.getValue(0, 2), 2.2);
        Assert.assertEquals(matrix1.getValue(1, 0), 3);
        Assert.assertEquals(matrix1.getValue(1, 1), 3.1);
        Assert.assertEquals(matrix1.getValue(1, 2), 3.2);
        Assert.assertEquals(matrix1.getValue(2, 0), 4);
        Assert.assertEquals(matrix1.getValue(2, 1), 4.1);
        Assert.assertEquals(matrix1.getValue(2, 2), 4.2);

        // matrix2
        Assert.assertEquals(matrix2.getValue(0, 0), 3);
        Assert.assertEquals(matrix2.getValue(0, 1), 3.1);
        Assert.assertEquals(matrix2.getValue(0, 2), 3.2);
        Assert.assertEquals(matrix2.getValue(1, 0), 4);
        Assert.assertEquals(matrix2.getValue(1, 1), 4.1);
        Assert.assertEquals(matrix2.getValue(1, 2), 4.2);
        Assert.assertEquals(matrix2.getValue(2, 0), 5);
        Assert.assertEquals(matrix2.getValue(2, 1), 5.1);
        Assert.assertEquals(matrix2.getValue(2, 2), 5.2);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testGetValueExceptionWhenColumnIsBeyondTheLimit() throws MatrixException {
        matrix1.getValue(0, 10);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testGetValueExceptionWhenRowIsBeyondTheLimit() throws MatrixException {
        matrix1.getValue(10, 0);
    }

    @Test
    public void testSetValue() throws MatrixException {
        matrix3.setValue(0, 0, 3.456);
        matrix3.setValue(0, 1, 3.456);
        matrix3.setValue(0, 2, 3.456);
        matrix3.setValue(1, 0, 3.456);
        matrix3.setValue(1, 1, 3.456);
        matrix3.setValue(1, 2, 3.456);

        Assert.assertEquals(matrix3.getValue(0, 0), 3.456);
        Assert.assertEquals(matrix3.getValue(0, 1), 3.456);
        Assert.assertEquals(matrix3.getValue(0, 2), 3.456);
        Assert.assertEquals(matrix3.getValue(1, 0), 3.456);
        Assert.assertEquals(matrix3.getValue(1, 1), 3.456);
        Assert.assertEquals(matrix3.getValue(1, 2), 3.456);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testSetValueExceptionWhenColumnIsBeyondTheLimit() throws MatrixException {
        matrix1.setValue(0, 10, 3.456);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testSetValueExceptionWhenRowIsBeyondTheLimit() throws MatrixException {
        matrix1.setValue(10, 0, 3.456);
    }

    @Test
    public void testAddition() throws MatrixException {
        Assert.assertEquals(matrix4.addition(matrix5), matrix6);
        Assert.assertEquals(matrix4, matrix6);
    }
    
    @Test(expectedExceptions = MatrixException.class)
    public void testAdditionExceptionWhenThisRowsIsNotEqualToPassedMatrixRows() throws MatrixException {
        matrix1.addition(matrix3);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testAdditionExceptionWhenThisColumnsIsNotEqualToPassedMatrixColumns() throws MatrixException {
        matrix1.addition(matrix3);
    }

    // TODO: Analogically, write tests for constructor creation, subtraction and multiplication
}