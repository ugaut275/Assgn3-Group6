package org.jfree.data;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import data.Range;

class ShiftTests {

	
	//ECP Tests
	@Test
    public void testShift_PositiveRange_Positive() {
        Range base = new Range(2, 5);
        Range shifted = Range.shift(base, 3, true);
        assertEquals(5, shifted.getLowerBound());
        assertEquals(8, shifted.getUpperBound());
    }

    @Test
    public void testShift_NegativeRange_Positive() {
        Range base = new Range(-5, -2);
        Range shifted = Range.shift(base, -3);
        assertEquals(-2, shifted.getLowerBound());
        assertEquals(1, shifted.getUpperBound());
    }

    @Test
    public void testShiftMixedRange_true() {
        Range base = new Range(-3, 3);
        Range shifted = Range.shift(base, 4, true);
        assertEquals(1, shifted.getLowerBound());
        assertEquals(7, shifted.getUpperBound());
    }

    @Test
    public void testShiftMixedRange_negative() {
        Range base = new Range(-3, 3);
        Range shifted = Range.shift(base, 4, false);
        assertEquals(0, shifted.getLowerBound());
        assertEquals(7, shifted.getUpperBound());
    }

    // Edge cases
    @Test
    public void testShiftZeroRange_Positive() {
        Range base = new Range(0, 0);
        Range shifted = Range.shift(base, 3, true);
        assertEquals(3, shifted.getLowerBound());
        assertEquals(3, shifted.getUpperBound());
    }

    @Test
    public void testShiftLarge_Positive() {
        Range base = new Range(1, 10);
        Range shifted = Range.shift(base, -2, false);
        assertEquals(-3, shifted.getLowerBound());
        assertEquals(0, shifted.getUpperBound());
    }
    
    
    //BVA
  
    @Test
    public void testShift_MinimumPositiveRange() {
        Range base = new Range(1, 2);
        Range shifted = Range.shift(base, 1, true);
        assertEquals(2, shifted.getLowerBound());
        assertEquals(3, shifted.getUpperBound());
    }

    @Test
    public void testShift_MaximumNegativeRange() {
        Range base = new Range(-2, -1);
        Range shifted = Range.shift(base, 1, true);
        assertEquals(-1, shifted.getLowerBound());
        assertEquals(0, shifted.getUpperBound());
    }

    @Test
    public void testShift_LowerBoundAtZero() {
        Range base = new Range(0, 5);
        Range shifted = Range.shift(base, 3, true);
        assertEquals(3, shifted.getLowerBound(), 0.001);
        assertEquals(8, shifted.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_UpperBoundAtZero() {
        Range base = new Range(-5, 0);
        Range shifted = Range.shift(base, 2, true);
        assertEquals(-3, shifted.getLowerBound(), 0.001);
        assertEquals(2, shifted.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_MixedRange_ZeroCrossing_Allowed() {
        Range base = new Range(-3, 3);
        Range shifted = Range.shift(base, 4, true);
        assertEquals(1, shifted.getLowerBound(), 0.001);
        assertEquals(7, shifted.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_MixedRange_ZeroCrossing_NotAllowed() {
        Range base = new Range(-3, 3);
        Range shifted = Range.shift(base, 4, false);
        assertEquals(0, shifted.getLowerBound(), 0.001);
        assertEquals(7, shifted.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_LargeDelta() {
        Range base = new Range(1, 10);
        Range shifted = Range.shift(base, 1e6, true);
        assertEquals(1000001, shifted.getLowerBound(), 0.001);
        assertEquals(1000010, shifted.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_LargeDelta_NoZeroCrossing() {
        Range base = new Range(-2, 2);
        Range shifted = Range.shift(base, 4, false);
        assertEquals(0, shifted.getLowerBound(), 0.001);
        assertEquals(6, shifted.getUpperBound(), 0.001);
    }

//    @Test
//    public void testShift_NullRange_ShouldThrowException() {
//        assertThrows(InvalidParameterException.class, () -> Range.shift(null, 3, true));
//    }
    
    
    
    
    

}
