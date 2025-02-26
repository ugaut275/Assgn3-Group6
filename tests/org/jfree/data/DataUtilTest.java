package org.jfree.data;

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
import data.KeyedValues;
import data.Values2D;

class DataUtilTest {

    private Values2D value;

    @BeforeEach
    void setUp() {
        value = mock(Values2D.class);
        when(value.getColumnCount()).thenReturn(4);
        when(value.getRowCount()).thenReturn(3);

        when(value.getValue(0, 2)).thenReturn(5);
        when(value.getValue(1, 2)).thenReturn(7);
        when(value.getValue(2, 2)).thenReturn(1);
    }


    @Test
    void testCalculateColumnTotal() {
        assertEquals(13, DataUtilities.calculateColumnTotal(value, 2), .01d);
        verify(value, times(3)).getValue(anyInt(), anyInt());
    }

    @Test
    void testCalculateRowTotal() {
        when(value.getValue(1, 0)).thenReturn(2);
        when(value.getValue(1, 1)).thenReturn(3);
        when(value.getValue(1, 2)).thenReturn(4);
        when(value.getValue(1, 3)).thenReturn(5);

        assertEquals(14, DataUtilities.calculateRowTotal(value, 1), .01d);
        verify(value, times(4)).getValue(anyInt(), anyInt());
    }

    @Test
    void testCreateNumberArray() {
        double[] data = {1.0, 2.0, 3.0};
        Number[] result = DataUtilities.createNumberArray(data);

        assertEquals(3, result.length);
        assertEquals(1.0, result[0].doubleValue(), .01d);
        assertEquals(2.0, result[1].doubleValue(), .01d);
        assertEquals(3.0, result[2].doubleValue(), .01d);
    }

    @Test
    void testCreateNumberArrayWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray(null);
        });
    }

    @Test
    void testCreateNumberArray2D() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
        assertEquals(1.0, result[0][0].doubleValue(), .01d);
        assertEquals(2.0, result[0][1].doubleValue(), .01d);
        assertEquals(3.0, result[1][0].doubleValue(), .01d);
        assertEquals(4.0, result[1][1].doubleValue(), .01d);
    }

    @Test
    void testCreateNumberArray2DWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray2D(null);
        });
    }

    @Test
    void testGetCumulativePercentages() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn("Key1");
        when(data.getKey(1)).thenReturn("Key2");
        when(data.getKey(2)).thenReturn("Key3");
        when(data.getValue(0)).thenReturn(5);
        when(data.getValue(1)).thenReturn(9);
        when(data.getValue(2)).thenReturn(2);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.3125, result.getValue("Key1").doubleValue(), .01d);
        assertEquals(0.875, result.getValue("Key2").doubleValue(), .01d);
        assertEquals(1.0, result.getValue("Key3").doubleValue(), .01d);
    }

    @Test
    void testGetCumulativePercentagesWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.getCumulativePercentages(null);
        });
    }
}