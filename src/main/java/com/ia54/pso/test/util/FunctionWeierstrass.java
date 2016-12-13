package com.ia54.pso.test.util;

import java.util.function.ToDoubleBiFunction;

public class FunctionWeierstrass implements FunctionPSO {

	@Override
	public double applyAsDouble(Float x, Float y) {
		double z = 0;
		for (int k=0; k<=20; ++k){
			    z=z+Math.pow(0.5,k)*Math.cos(2*Math.PI*Math.pow(3,k)*(x+0.5))+Math.pow(0.5,k)*Math.cos(2*Math.PI*Math.pow(3,k)*(y+0.5));
		}
		double temp=0;
		for (int k=0; k<=20; ++k){
			temp=temp+Math.pow(0.5,k)*Math.cos(2*Math.PI*Math.pow(3,k)*0.5);
		}
		
		return z-2*temp;
	}
	@Override
	public String toString(){
			return "Weierstrass";
		}	
		
		public float[] interval()
		{
			
			float d[] = {-0.5f,0.5f,-0.5f,0.5f};
			return d;
		}
		
		public float[] interval2()
		{
			
			float d[] = {0,0.5f,0,0.5f};
			return d;
		}
		
	

}
