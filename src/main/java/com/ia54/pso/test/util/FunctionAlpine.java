package com.ia54.pso.test.util;

public class FunctionAlpine implements FunctionPSO {

	@Override
	public double applyAsDouble(Float x, Float y) {
		// TODO Auto-generated method stub
		
		return  Math.sin(x)*Math.sin(y)*Math.sqrt(x*y);
	}
	@Override
	public String toString(){
	
		return "sin(x)*sin(y)*sqrt(x*y)";
	}

	@Override
	public float[] interval() {
		
		float d[] = {0,25,0,25};
		return d;
	}

	@Override
	public float[] interval2() {
		float d[] = {-0.5f,0.5f,-0.5f,0.5f};
		return d;
	}

}
