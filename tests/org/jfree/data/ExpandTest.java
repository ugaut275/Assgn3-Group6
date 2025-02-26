package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

import data.Range;

import java.security.InvalidParameterException;



/**
 * JUnit 5 test class for the {@code expand} method of the {@code Range} class.
 * This class applies Equivalence Class Partitioning (ECP) and Boundary Value Analysis (BVA)
 * to verify the correctness of the {@code expand} method.
 * 
 * @author Utsav Gautam
 * @version 1.0
 */
class ExpandTest {

    @Test
    void testExpand_PositiveMargins() {
        Range range = new Range(2, 6);
        Range expandedRange = Range.expand(range, 0.25, 0.5);
        assertEquals(new Range(1, 8), expandedRange);
    }

    @Test
    void testExpand_ZeroMargins() {
        Range range = new Range(2, 6);
        Range expandedRange = Range.expand(range, 0, 0);
        assertEquals(new Range(2, 6), expandedRange);
    }

    @Test
    void testExpand_NegativeMargins() {
        Range range = new Range(2, 6);
        Range expandedRange = Range.expand(range, -0.25, -0.5);
        assertEquals(new Range(3, 4), expandedRange);
    }

    @Test
    void testExpand_MixedMargins() {
        Range range = new Range(2, 6);
        Range expandedRange = Range.expand(range, 0.25, -0.5);
        assertEquals(new Range(1, 4), expandedRange);
    }

    @Test
    void testExpand_NullRange() {
        assertThrows(InvalidParameterException.class, () -> {
            Range.expand(null, 0.25, 0.5);
        });
    }

    @Test
    void testExpand_InvalidMargins() {
        Range range = new Range(2, 6);
        assertThrows(InvalidParameterException.class, () -> {
            Range.expand(range, -1.5, -1.5); 
        });
    }

    @Test
    void testExpand_MinMaxDoubleValues() {
        Range range = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        Range expandedRange = Range.expand(range, 0.1, 0.1);
        double expectedLower = Double.MIN_VALUE - (Double.MAX_VALUE - Double.MIN_VALUE) * 0.1;
        double expectedUpper = Double.MAX_VALUE + (Double.MAX_VALUE - Double.MIN_VALUE) * 0.1;
        assertEquals(new Range(expectedLower, expectedUpper), expandedRange);
    }

    @Test
    void testExpand_MarginsCloseToZero() {
        Range range = new Range(2, 6);
        Range expandedRange = Range.expand(range, 0.0001, -0.0001);
        double expectedLower = 2 - (6 - 2) * 0.0001;
        double expectedUpper = 6 + (6 - 2) * -0.0001;
        assertEquals(new Range(expectedLower, expectedUpper), expandedRange);
    }

    @Test
    void testExpand_MarginsEqualToOne() {
        Range range = new Range(2, 6);
        Range expandedRange = Range.expand(range, 1.0, 1.0);
        assertEquals(new Range(-2, 10), expandedRange);
    }

    @Test
    void testExpand_MarginsGreaterThanOne() {
        Range range = new Range(2, 6);
        Range expandedRange = Range.expand(range, 1.5, 1.5);
        assertEquals(new Range(-4, 12), expandedRange);
    }
}