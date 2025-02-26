package org.jfree.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;

/**
 * JUnit 5 test class for the {@code getUpperBound()} method of the {@code Range} class.
 * <p>
 * This class applies Boundary Value Analysis (BVA) and Exception Testing to verify the correctness 
 * of {@code getUpperBound()} based on the Javadoc specifications.
 * </p>
 * 
 * <b>Test Cases:</b>
 * - Test exactly at the upper boundary.
 * - Test just below and just above the upper boundary.
 * - Test extreme values such as {@code Double.MAX_VALUE}, {@code Double.POSITIVE_INFINITY}.
 * - Test invalid ranges where `lower > upper`.
 * <p>
 * Note: The expected values are derived from the method specification, not the actual implementation.
 * </p>
 * 
 * @author Anmol Verma
 * @version 1.0
 */
class GetUpperBoundTests {

    private Range boundaryRange;
    private Range maxValueRange;
    private Range positiveInfinityRange;
    private Range zeroRange;
    private Range negativeRange;
    private Range equalBoundsRange;
    private Range largeRange;

    @BeforeEach
    void setUp() {
        boundaryRange = new Range(-5, 10);
        maxValueRange = new Range(-10, Double.MAX_VALUE);
        positiveInfinityRange = new Range(-10, Double.POSITIVE_INFINITY);
        zeroRange = new Range(-10, 0);
        negativeRange = new Range(-100, -10);
        equalBoundsRange = new Range(-5, -5);
        largeRange = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
    }

    @Test
    void testGetUpperBound_AtBoundary() {
        assertEquals(10, boundaryRange.getUpperBound(), 0.01);
    }

    @Test
    void testGetUpperBound_JustBelowBoundary() {
        Range testRange = new Range(-5, 9.99);
        assertEquals(9.99, testRange.getUpperBound(), 0.01);
    }

    @Test
    void testGetUpperBound_JustAboveBoundary() {
        Range testRange = new Range(-5, 10.01);
        assertEquals(10.01, testRange.getUpperBound(), 0.01);
    }

    @Test
    void testGetUpperBound_MaxValue() {
        assertEquals(Double.MAX_VALUE, maxValueRange.getUpperBound(), 0.01);
    }

    @Test
    void testGetUpperBound_PositiveInfinity() {
        assertEquals(Double.POSITIVE_INFINITY, positiveInfinityRange.getUpperBound(), 0.01);
    }

    @Test
    void testGetUpperBound_Zero() {
        assertEquals(0, zeroRange.getUpperBound(), 0.01);
    }

    @Test
    void testGetUpperBound_NegativeRange() {
        assertEquals(-10, negativeRange.getUpperBound(), 0.01);
    }

    @Test
    void testGetUpperBound_EqualBounds() {
        assertEquals(-5, equalBoundsRange.getUpperBound(), 0.01);
    }

    @Test
    void testGetUpperBound_LargeRange() {
        assertEquals(Double.MAX_VALUE, largeRange.getUpperBound(), 0.01);
    }

    @Test
    void testInvalidRange_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Range(5, -5);
        });

        System.out.println("testInvalidRange_ShouldThrowException: Caught Exception = " + exception.getMessage());
    }
}
