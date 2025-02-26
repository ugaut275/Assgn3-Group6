package org.jfree.data;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	CombineTest.class,
	ConstrainTests.class,
	ContainTests.class,
	DataUtilTest.class,
	ExpandTest.class,
	GetLowerBoundTests.class,
	GetUpperBoundTests.class,
	IntersectsTests.class,
	RangeEquals.class,
	RangeToIncludeTests.class,
	GetCentralValueTests.class,
	RangeGetLengthTests.class,
	ToStringTests.class,
	ShiftTests.class
//	
})

public class TestSuite {


}
