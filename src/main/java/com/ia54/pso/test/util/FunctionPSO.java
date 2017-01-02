package com.ia54.pso.test.util;

import java.util.function.ToDoubleBiFunction;

public interface FunctionPSO extends ToDoubleBiFunction<Float, Float> {

	public float[] interval();
	public float[] interval2();

}
