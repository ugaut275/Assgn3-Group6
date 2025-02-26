package org.jfree.data;
import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;

class ContainTests {

	Range exampleRange1;
	double exampleInput;
	
	@BeforeEach
	void setUp() throws Exception {
		exampleRange1 = new Range(1,10);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void ValidInputInsideRange() {
		exampleInput = 5; 
		boolean actual = exampleRange1.contains(exampleInput);
		assertTrue(actual);
	}	
	
	@Test
	void ExactlyAtLowerBoundary() {
		exampleInput = 1;
		boolean actual = exampleRange1.contains(exampleInput);
		assertTrue(actual);
	}
	@Test
	void ExactlyAtUpperBoundary() {
		exampleInput = 10;
		boolean actual = exampleRange1.contains(exampleInput);
		assertTrue(actual);
	}
	
	@Test
	void AboveUpperBoundary() {
		exampleInput = 11;
		boolean actual = exampleRange1.contains(exampleInput);
		assertFalse(actual);
	}	
	
	@Test
	void BelowLowerBoundary() {
		exampleInput = 0;
		boolean actual = exampleRange1.contains(exampleInput);
		assertFalse(actual);
	}
	
	@Test
	void JustBelowLowerBoundary() {
		exampleInput = 0.99;
		boolean actual = exampleRange1.contains(exampleInput);
		assertFalse(actual);
	}
	
	@Test
	void JustAboveLowerBoundary() {
		exampleInput = 1.01;
		boolean actual = exampleRange1.contains(exampleInput);
		assertTrue(actual);
	}
	
	@Test
	void JustBelowUpperBoundary() {
		exampleInput = 9.99;
		boolean actual = exampleRange1.contains(exampleInput);
		assertTrue(actual);
	}
	
	@Test
	void JustAboveUpperBoundary() {
		exampleInput = 10.01;
		boolean actual = exampleRange1.contains(exampleInput);
		assertFalse(actual);
	}

}
