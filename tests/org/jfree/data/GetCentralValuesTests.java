package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;

/**
 * JUnit 5 test class for the {@code getCentralValue} method of the {@code Range} class.
 * This class applies Equivalence Class Partitioning (ECP) and Boundary Value Analysis (BVA)
 * to verify the correctness of the {@code getCentralValue} method.
 * 
 * @author Utsav Gautam
 * @version 1.0
 */
class GetCentralValueTests {

    private Range positiveRange;
    private Range negativeRange;
    private Range mixedRange;
    private Range singlePointRange;
    private Range minMaxRange;
    private Range zeroRange;
    private Range smallRange;
    private Range largeRange;

    @BeforeEach
    void setUp() {
        positiveRange = new Range(2, 6);
        negativeRange = new Range(-6, -2);
        mixedRange = new Range(-4, 4);
        singlePointRange = new Range(5, 5);
        minMaxRange = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        zeroRange = new Range(-10, 10);
        smallRange = new Range(1.0001, 1.0002);
        largeRange = new Range(1e100, 2e100);
    }

    @Test
    void testGetCentralValue_PositiveRange() {
        assertEquals(4, positiveRange.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValue_NegativeRange() {
        assertEquals(-4, negativeRange.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValue_MixedRange() {
        assertEquals(0, mixedRange.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValue_SinglePointRange() {
        assertEquals(5, singlePointRange.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValue_MinMaxRange() {
        double expectedCentralValue = (Double.MIN_VALUE + Double.MAX_VALUE) / 2;
        assertEquals(expectedCentralValue, minMaxRange.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValue_ZeroRange() {
        assertEquals(0, zeroRange.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValue_SmallRange() {
        assertEquals(1.00015, smallRange.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValue_LargeRange() {
        assertEquals(1.5e100, largeRange.getCentralValue(), 0.0001);
    }
}