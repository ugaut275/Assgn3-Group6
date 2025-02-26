package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;

/**
 * JUnit 5 test class for the {@code expandToInclude} method of the {@code Range} class.
 * This class applies Equivalence Class Partitioning (ECP) and Boundary Value Analysis (BVA)
 * to verify the correctness of the {@code expandToInclude} method.
 * 
 * @author Utsav Gautam
 * @version 1.0
 */
class RangeToIncludeTests {

    private Range range;
    private Range minMaxRange;

    @BeforeEach
    void setUp() {
        range = new Range(2, 6);

        minMaxRange = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
    }

    @Test
    void testExpandToInclude_NullRange() {
        Range expandedRange = Range.expandToInclude(null, 5);
        assertEquals(new Range(5, 5), expandedRange);
    }

    @Test
    void testExpandToInclude_ValueBelowLowerBound() {
        Range expandedRange = Range.expandToInclude(range, 1);
        assertEquals(new Range(1, 6), expandedRange);
    }

    @Test
    void testExpandToInclude_ValueAboveUpperBound() {
        Range expandedRange = Range.expandToInclude(range, 7);
        assertEquals(new Range(2, 7), expandedRange);
    }

    @Test
    void testExpandToInclude_ValueWithinRange() {
        Range expandedRange = Range.expandToInclude(range, 4);
        assertEquals(new Range(2, 6), expandedRange);
    }

    @Test
    void testExpandToInclude_ValueEqualToLowerBound() {
        Range expandedRange = Range.expandToInclude(range, 2);
        assertEquals(new Range(2, 6), expandedRange);
    }

    @Test
    void testExpandToInclude_ValueEqualToUpperBound() {
        Range expandedRange = Range.expandToInclude(range, 6);
        assertEquals(new Range(2, 6), expandedRange);
    }

    @Test
    void testExpandToInclude_NaNValue() {
        Range expandedRange = Range.expandToInclude(range, Double.NaN);
        assertEquals(new Range(2, 6), expandedRange);
    }

    @Test
    void testExpandToInclude_MinMaxDoubleValues() {
        Range expandedRange = Range.expandToInclude(minMaxRange, 0);
        assertEquals(new Range(Double.MIN_VALUE, Double.MAX_VALUE), expandedRange);
    }

    @Test
    void testExpandToInclude_ValueJustBelowLowerBound() {
        Range expandedRange = Range.expandToInclude(range, 1.9999);
        assertEquals(new Range(1.9999, 6), expandedRange);
    }

    @Test
    void testExpandToInclude_ValueJustAboveUpperBound() {
        Range expandedRange = Range.expandToInclude(range, 6.0001);
        assertEquals(new Range(2, 6.0001), expandedRange);
    }
}