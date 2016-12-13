package com.ia54.pso.test.util;

import java.util.function.ToDoubleBiFunction;

public class FunctionAckley implements FunctionPSO {

	@Override
	public double applyAsDouble(Float x, Float y) {
		// TODO Auto-generated method stub
		double temp1=Math.pow(x,2)+Math.pow(y,2);
		double temp2=Math.cos(2*Math.PI*x)+Math.cos(2*Math.PI*y);

		return 20+Math.E-20*Math.exp(-0.2*Math.sqrt(temp1/2))-Math.exp(temp2/2);
	}
	@Override
	public String toString(){
		return "20+exp(1)-20*exp(-0.2*sqrt((x.^2+y.^2)*1/2))-exp((cos(2*pi*x)+cos(2*pi*y))/2)";
	}

	@Override
	public float[] interval() {
		float d[] = {-5,5,-5,5};
		return d;

	}

	@Override
	public float[] interval2() {
		// TODO Auto-generated method stub
		return null;
	}

}


