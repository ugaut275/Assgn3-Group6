package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;

class CombineTest {

	Range exampleRange1;
	Range exampleRange2;
	
	
	@BeforeEach
	void setUp() throws Exception {
		exampleRange1 = new Range(2,6);
		exampleRange2 = new Range(4,10);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void combineOneNullRange() {
		Range actual = Range.combine(exampleRange1, null);
		Range expected = new Range(2,6);
		assertEquals(expected,actual);
	}
	
	@Test
	void combineTwoNullRanges() {
		Range actual = Range.combine(null, null);
		
		assertNull(actual);
	}
	
	@Test
	void combineMatchingRange() {
		Range actual = Range.combine(exampleRange1, new Range(2,6));
		Range expected = new Range(2,6);
		
		assertEquals(expected,actual);
	}
	
	@Test
	void combineValidOverlappingRange() {
		Range actual = Range.combine(exampleRange1, exampleRange2);
		Range expected = new Range(2,10);
		assertEquals(expected,actual);
	}
	
	@Test
	void combineValidNonOverlappingRange() {
		Range actual = Range.combine(exampleRange1, new Range(7,10));
		Range expected = new Range(2,10);
		
		assertEquals(expected,actual);
	}
	
	@Test
	void combineOneRangeLowLowerBound() {
		Range actual = Range.combine(new Range(-1000000000,3),exampleRange2);
		Range expected = new Range(-1000000000,10);
		
		assertEquals(expected,actual);
	}
	
	@Test
	void combineOneRangeHighUpperBound() {
		Range actual = Range.combine(exampleRange1, new Range(7,100000000));
		Range expected = new Range(2,100000000);
		
		assertEquals(expected,actual);
	}
	
	@Test 
	void combineOneLowerBoundZero() {
		Range actual = Range.combine(new Range(0,2), exampleRange2);
		Range expected = new Range(0,10);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void combineOneHigherBoundZero() {
		Range actual = Range.combine(new Range(-10,0), exampleRange2 );
		Range expected = new Range(-10 ,10);
		
		assertEquals(expected, actual);
	}

}