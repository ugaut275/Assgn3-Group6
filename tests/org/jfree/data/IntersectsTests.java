package org.jfree.data;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;

/**
 * JUnit 5 test class for the {@code intersects()} method of the {@code Range} class.
 * <p>
 * This class applies Boundary Value Analysis (BVA) and Edge Case Testing to
 * verify the correctness of {@code intersects()} based on the Javadoc specifications.
 * </p>
 * 
 * <b>Test Cases:</b>
 * - Test overlapping, non-overlapping, and touching boundary cases.
 * - Test extreme values such as {@code Double.POSITIVE_INFINITY}, {@code Double.NEGATIVE_INFINITY}.
 * - Test invalid ranges where {@code lower > upper}.
 * <p>
 * Note: The expected values are derived from the method specification, not the actual implementation.
 * </p>
 * 
 * @author Anmol Verma
 * @version 1.1
 */
class IntersectsTests {

	private Range testRange;

	/**
	 * Initializes the test range before each test.
	 */
	@BeforeEach
	void setUp() {
		testRange = new Range(-5, 10); // Standard test range
	}

	/**
	 * Tests {@code intersects()} with an exact overlapping range. Expected Result: {@code true}.
	 */
	@Test
	void testIntersects_ExactOverlap() {
		assertTrue(testRange.intersects(-5, 10));
	}

	/**
	 * Tests {@code intersects()} with a partially overlapping range (lower bound). Expected Result: {@code true}.
	 */
	@Test
	void testIntersects_PartialOverlapLower() {
		assertTrue(testRange.intersects(-10, 0));
	}

	/**
	 * Tests {@code intersects()} with a partially overlapping range (upper bound). Expected Result: {@code true}.
	 */
	@Test
	void testIntersects_PartialOverlapUpper() {
		assertTrue(testRange.intersects(5, 15));
	}

	/**
	 * Tests {@code intersects()} with a completely non-overlapping range. Expected Result: {@code false}.
	 */
	@Test
	void testIntersects_NoOverlap() {
		assertFalse(testRange.intersects(20, 30));
	}

	@Test
	void testIntersects_TouchesLowerBoundary() {
	    assertFalse(testRange.intersects(-10, -5)); // Expected to return false
	}

	@Test
	void testIntersects_TouchesUpperBoundary() {
	    assertFalse(testRange.intersects(10, 15)); // Expected to return false
	}


	/**
	 * Tests {@code intersects()} with an invalid range where {@code lower > upper}.
	 * Expected Result: {@code false} or an exception, depending on implementation.
	 */
	@Test
	void testIntersects_InvalidRange() {
		assertFalse(testRange.intersects(5, -5));
	}

	/**
	 * Tests {@code intersects()} with an extremely large range covering the entire space. Expected Result: {@code true}.
	 */
	@Test
	void testIntersects_LargeRange() {
		assertTrue(testRange.intersects(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
	}

	/**
	 * Tests {@code intersects()} when the range is a single point inside the test range. Expected Result: {@code true}.
	 */
	@Test
	void testIntersects_SinglePointInside() {
		assertTrue(testRange.intersects(0, 0));
	}

	/**
	 * Tests {@code intersects()} when the range is a single point outside the test range. Expected Result: {@code false}.
	 */
	@Test
	void testIntersects_SinglePointOutside() {
		assertFalse(testRange.intersects(20, 20));
	}

	/**
	 * Tests {@code intersects()} with a range completely inside the test range. Expected Result: {@code true}.
	 */
	@Test
	void testIntersects_InsideRange() {
		assertTrue(testRange.intersects(-2, 5));
	}

	/**
	 * Tests {@code intersects()} when both the lower and upper bounds are outside but overlap the range. Expected Result: {@code true}.
	 */
	@Test
	void testIntersects_OverlappingBothSides() {
		assertTrue(testRange.intersects(-10, 15));
	}

	@Test
	void testIntersects_LowerBoundAtUpperLimit() {
	    assertFalse(testRange.intersects(10, 15)); // New logic makes this false
	}

	@Test
	void testIntersects_UpperBoundAtLowerLimit() {
	    assertFalse(testRange.intersects(-10, -5)); // New logic makes this false
	}

	/**
	 * Tests {@code intersects()} with values that are exactly on the boundaries. Expected Result: {@code true}.
	 */
	@Test
	void testIntersects_OnBoundaries() {
		assertTrue(testRange.intersects(-5, 10));
	}

	/**
	 * Tests {@code intersects()} with extremely large numbers beyond normal floating point ranges. Expected Result: {@code false}.
	 */
	@Test
	void testIntersects_ExtremeValues() {
		assertFalse(testRange.intersects(Double.MAX_VALUE, Double.MAX_VALUE));
		assertFalse(testRange.intersects(-Double.MAX_VALUE, -Double.MAX_VALUE));
	}

	/**
	 * Tests {@code intersects()} with NaN values, which should return false or throw an exception depending on implementation.
	 */
	@Test
	void testIntersects_NaNValues() {
		assertFalse(testRange.intersects(Double.NaN, Double.NaN));
		assertFalse(testRange.intersects(Double.NaN, 10));
		assertFalse(testRange.intersects(-5, Double.NaN));
	}

}
