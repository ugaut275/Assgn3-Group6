package org.jfree.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;

/**
 * JUnit 5 test class for the {@code getLowerBound()} method of the {@code Range} class.
 * <p>
 * This class applies Boundary Value Analysis (BVA) and Exception Testing to verify the correctness 
 * of {@code getLowerBound()} based on the Javadoc specifications.
 * </p>
 * 
 * <b>Test Cases:</b>
 * - Test exactly at the lower boundary.
 * - Test just below and just above the lower boundary.
 * - Test extreme values such as {@code Double.MIN_VALUE}, {@code Double.NEGATIVE_INFINITY}.
 * - Test invalid ranges where `lower > upper`.
 * <p>
 * Note: The expected values are derived from the method specification, not the actual implementation.
 * </p>
 * 
 * @author Anmol Verma
 * @version 1.0
 */
class GetLowerBoundTests {

    private Range boundaryRange;
    private Range minValueRange;
    private Range negativeInfinityRange;
    private Range zeroRange;
    private Range negativeRange;
    private Range equalBoundsRange;
    private Range largeRange;

    /**
     * Initializes test data before each test execution.
     * Sets up different {@code Range} instances for boundary testing.
     */
    @BeforeEach
    void setUp() {
        boundaryRange = new Range(-5, 10); // Standard test case
        minValueRange = new Range(Double.MIN_VALUE, 10); // Smallest positive double
        negativeInfinityRange = new Range(Double.NEGATIVE_INFINITY, 10); // Edge case
        zeroRange = new Range(0, 10); // Testing zero as lower bound
        negativeRange = new Range(-100, -10); // Testing negative range
        equalBoundsRange = new Range(-5, -5); // Edge case where lower == upper
        largeRange = new Range(Double.MIN_VALUE, Double.MAX_VALUE); // Large range test
    }

    @Test
    void testGetLowerBound_AtBoundary() {
        assertEquals(-5, boundaryRange.getLowerBound(), 0.01);
    }

    @Test
    void testGetLowerBound_JustBelowBoundary() {
        Range testRange = new Range(-5.01, 10);
        assertEquals(-5.01, testRange.getLowerBound(), 0.01);
    }

    @Test
    void testGetLowerBound_JustAboveBoundary() {
        Range testRange = new Range(-4.99, 10);
        assertEquals(-4.99, testRange.getLowerBound(), 0.01);
    }

    @Test
    void testGetLowerBound_MinValue() {
        assertEquals(Double.MIN_VALUE, minValueRange.getLowerBound(), 0.01);
    }

    @Test
    void testGetLowerBound_NegativeInfinity() {
        assertEquals(Double.NEGATIVE_INFINITY, negativeInfinityRange.getLowerBound(), 0.01);
    }

    @Test
    void testGetLowerBound_Zero() {
        assertEquals(0, zeroRange.getLowerBound(), 0.01);
    }

    @Test
    void testGetLowerBound_NegativeRange() {
        assertEquals(-100, negativeRange.getLowerBound(), 0.01);
    }

    @Test
    void testGetLowerBound_EqualBounds() {
        assertEquals(-5, equalBoundsRange.getLowerBound(), 0.01);
    }

    @Test
    void testGetLowerBound_LargeRange() {
        assertEquals(Double.MIN_VALUE, largeRange.getLowerBound(), 0.01);
    }

    /**
     * Tests invalid case where lower bound is greater than upper bound.
     * Expecting an exception.
     */
    @Test
    void testInvalidRange_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Range(5, -5); // Invalid range where lower > upper
        });

        System.out.println("testInvalidRange_ShouldThrowException: Caught Exception = " + exception.getMessage());
    }
}
