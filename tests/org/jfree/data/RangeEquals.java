package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;
//utsav gautam
class RangeEquals {
		private Range range1;
	    private Range range2;
	    private Range range3;
	    private Range range4;
	    private Range range5;
	@BeforeEach
	void setUp() throws Exception {
	      	range1 = new Range(0, 10);
	        range2 = new Range(0, 10);
	        range3 = new Range(-5, 5);
	        range4 = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
	        range5 = new Range(5, 5);
	}

  @Test
    void testEquals_SameObject() {
        assertTrue(range1.equals(range1));
    }
  // ECP: Identical Range
    @Test
    void testEquals_IdenticalObject() {
        assertTrue(range1.equals(range2));
    }

    // ECP: Different Range
    @Test
    void testEquals_DifferentBounds() {
        assertFalse(range1.equals(range3));
    }

    // ECP: Null Object
    @Test
    void testEquals_Null() {
        assertFalse(range1.equals(null));
    }

    // ECP: Different Class
    @Test
    void testEquals_DifferentClass() {
        assertFalse(range1.equals(new String("Range")));
    }

    // BVA: Equal Lower and Upper Bounds
    @Test
    void testEquals_EqualBounds() {
        assertTrue(range5.equals(new Range(5, 5)));
    }

    // BVA: Minimum and Maximum Double Values
    @Test
    void testEquals_MinMaxDoubleValues() {
        assertTrue(range4.equals(new Range(Double.MIN_VALUE, Double.MAX_VALUE)));
    }

     //BVA: Just Above Boundary
    @Test
    void testEquals_JustAboveBoundary() {
        assertFalse(range1.equals(new Range(0, 10.0001)));
    }

     //BVA: Just Below Boundary
    @Test
    void testEquals_JustBelowBoundary() {
        assertFalse(range1.equals(new Range(0, 9.9999)));
    }

    // ECP: Symmetric Property
    @Test
    void testEquals_Symmetric() {
        assertTrue(range1.equals(range2));
        assertTrue(range2.equals(range1));
    }

    // ECP: Transitive Property
    @Test
    void testEquals_Transitive() {
        Range range6 = new Range(0, 10);
        assertTrue(range1.equals(range2));
        assertTrue(range2.equals(range6));
        assertTrue(range1.equals(range6));
    }

    // ECP: Consistent Property
    @Test
    void testEquals_Consistent() {
        assertTrue(range1.equals(range2));
        assertTrue(range1.equals(range2)); // Multiple calls should return the same result
    }

}
