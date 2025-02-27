package org.jfree.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.DataUtilities;
import data.DefaultKeyedValues;
import data.KeyedValues;
import data.Values2D;

class DataUtilTest {
	
	@Test
    void testCreateNumberArrayWithValidInput() {
        double[] data = {1.1, 2.2, 3.3};
        Number[] result = DataUtilities.createNumberArray(data);
        assertArrayEquals(new Number[]{1.1, 2.2, 3.3}, result);
    }

    @Test
    void testCreateNumberArrayWithEmptyArray() {
        double[] data = {};
        Number[] result = DataUtilities.createNumberArray(data);
        assertArrayEquals(new Number[]{}, result);
    }

    @Test
    void testCreateNumberArrayWithNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray(null);
        });
        assertTrue(exception.getMessage().contains("Null"));
    }

    @Test
    void testCreateNumberArray2DWithValidInput() {
        double[][] data = {{1.1, 2.2}, {3.3, 4.4}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertArrayEquals(new Number[][]{{1.1, 2.2}, {3.3, 4.4}}, result);
    }

    @Test
    void testCreateNumberArray2DWithEmptyArray() {
        double[][] data = {};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertArrayEquals(new Number[][]{}, result);
    }

    @Test
    void testCreateNumberArray2DWithNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray2D(null);
        });
        assertTrue(exception.getMessage().contains("Null"));
    }

    @Test
    void testGetCumulativePercentagesWithValidData() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 5);
        data.addValue("B", 9);
        data.addValue("C", 2);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.3125, result.getValue("A").doubleValue(), 0.00001);
        assertEquals(0.875, result.getValue("B").doubleValue(), 0.00001);
        assertEquals(1.0, result.getValue("C").doubleValue(), 0.00001);
    }

    @Test
    void testGetCumulativePercentagesWithZeroValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 0);
        data.addValue("B", 0);
        data.addValue("C", 0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertTrue(Double.isNaN(result.getValue("A").doubleValue()));
        assertTrue(Double.isNaN(result.getValue("B").doubleValue()));
        assertTrue(Double.isNaN(result.getValue("C").doubleValue()));
    }

    @Test
    void testCalculateColumnTotalWithMultipleColumns() {
        Values2D data = mock(Values2D.class);
        when(data.getRowCount()).thenReturn(3);
        when(data.getValue(0, 0)).thenReturn(5);
        when(data.getValue(1, 0)).thenReturn(9);
        when(data.getValue(2, 1)).thenReturn(2);

        double result = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(14.0, result);
    }

    @Test
    void testCalculateRowTotalWithMultipleRows() {
        Values2D data = mock(Values2D.class);
        when(data.getColumnCount()).thenReturn(3);
        when(data.getValue(0, 0)).thenReturn(4);
        when(data.getValue(0, 1)).thenReturn(7);
        when(data.getValue(0, 2)).thenReturn(3);
        when(data.getValue(1, 0)).thenReturn(2);
        when(data.getValue(1, 1)).thenReturn(6);
        when(data.getValue(1, 2)).thenReturn(1);

        double result = DataUtilities.calculateRowTotal(data, 1);
        assertEquals(9.0, result);
    }
}