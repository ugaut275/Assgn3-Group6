package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;

/**
 * JUnit 5 test class for the {@code getLength} method of the {@code Range} class.
 * This class applies Equivalence Class Partitioning (ECP) and Boundary Value Analysis (BVA)
 * to verify the correctness of the {@code getLength} method.
 * 
 * @author Utsav Gautam
 * @version 1.0
 */
class RangeGetLengthTests {

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
    void testGetLength_PositiveRange() {
        assertEquals(4, positiveRange.getLength(), 0.0001);
    }

    @Test
    void testGetLength_NegativeRange() {
        assertEquals(4, negativeRange.getLength(), 0.0001);
    }

    @Test
    void testGetLength_MixedRange() {
        assertEquals(8, mixedRange.getLength(), 0.0001);
    }

    @Test
    void testGetLength_SinglePointRange() {
        assertEquals(0, singlePointRange.getLength(), 0.0001);
    }

    @Test
    void testGetLength_MinMaxRange() {
        double expectedLength = Double.MAX_VALUE - Double.MIN_VALUE;
        assertEquals(expectedLength, minMaxRange.getLength(), 0.0001);
    }

    @Test
    void testGetLength_ZeroRange() {
        assertEquals(20, zeroRange.getLength(), 0.0001);
    }

    @Test
    void testGetLength_SmallRange() {
        assertEquals(0.0001, smallRange.getLength(), 0.0001);
    }

    @Test
    void testGetLength_LargeRange() {
        assertEquals(1e100, largeRange.getLength(), 0.0001);
    }
}