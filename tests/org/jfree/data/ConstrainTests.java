package org.jfree.data;
import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Range;

class ConstrainTests {
	
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
		exampleInput = 4;
		double actual = exampleRange1.constrain(exampleInput);
		double expected = 4;
		assertEquals(expected,actual);
	}
	
	@Test
	void ValueAtLowerBoundary() {
		exampleInput = 1; 
		double actual = exampleRange1.constrain(exampleInput);
		double expected = 1; 
		assertEquals(expected, actual);
	}
	
	@Test
	void ValueAtUpperBoundary() {
		exampleInput = 10; 
		double actual = exampleRange1.constrain(exampleInput);
		double expected = 10; 
		assertEquals(expected, actual);
	}
	
	@Test
	void ValueBelowLowerRange() {
		exampleInput = 0; 
		double actual = exampleRange1.constrain(exampleInput);
		double expected = 1; 
		assertEquals(expected, actual);
	}
	
	@Test
	void ValueAboveUpperRange() {
		exampleInput = 15; 
		double actual = exampleRange1.constrain(exampleInput);
		double expected = 10; 
		assertEquals(expected, actual);
	}
	
	@Test
	void JustBelowLowerBoundary() {
		exampleInput = 0.99;
		double actual = exampleRange1.constrain(exampleInput);
		double expected = 1;
		assertEquals(expected, actual);
	}
	
	@Test
	void JustAboveUpperBoundary() {
		exampleInput = 10.01;
		double actual = exampleRange1.constrain(exampleInput);
		double expected = 10;
		assertEquals(expected, actual);
	}
	
	@Test
	void JustBelowUpperBoundary() {
		exampleInput = 9.99;
		double actual = exampleRange1.constrain(exampleInput);
		double expected = 9.99;
		assertEquals(expected, actual);
	}
	
	@Test
	void JustAboveLowerBoundary() {
		exampleInput = 1.01;
		double actual = exampleRange1.constrain(exampleInput);
		double expected = 1.01;
		assertEquals(expected, actual);
	}
	

}
