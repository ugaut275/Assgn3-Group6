package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;

import data.Range;

class ToStringTests {

@Test
    public void testToString() {
        Range range = new Range(2, 10);
        assertEquals("Range[2.0,10.0]", range.toString());
    }

@Test
    public void testToStringNegative() {
        Range range = new Range(-10, -2);
        assertEquals("Range[-10.0,-2.0]", range.toString());
    }

@Test
    public void testToStringZero() {
        Range range = new Range(0, 0);
        assertEquals("Range[0.0,0.0]", range.toString());
    }

@Test
    public void testToStringSameRange() {
        Range range = new Range(5, 5);
        assertEquals("Range[5.0,5.0]", range.toString());
    }

@Test
public void MinimumPositiveRange() {
        Range range = new Range(1, 2);
        assertEquals("Range[1,2]", range.toString());
    }

@Test
public void MinimumNegativeRange() {
        Range range = new Range(-2, -1);
        assertEquals("Range[-2,-1]", range.toString());
    }

@Test
public void UpperBound() {
        Range range = new Range(-5, 0);
        assertEquals("Range[-5,0]", range.toString());
    }

@Test
public void LowerBound() {
        Range range = new Range(0, 5);
        assertEquals("Range[0,5]", range.toString());
    }

@Test
public void largePositiveValue() {
Range range = new Range(1000000.0,1000001.0);
assertEquals("Range[1000000.0,1000001.0]", range.toString());
}

@Test
public void largeNegativeValue() {
Range range = new Range(-1000000.0,-1000001.0);
assertEquals("Range[-1000000.0,-1000001.0]", range.toString());
}


@Test
public void testToStringWhen() {
	Range mockRange = mock(Range.class);
	when(mockRange.toString()).thenReturn("Range[2.0,10.0]");
    assertEquals("Range[2.0,10.0]", mockRange.toString());
}

}