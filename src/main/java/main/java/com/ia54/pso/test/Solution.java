package main.java.com.ia54.pso.test;

public class Solution {
	public float x;
	public Solution(){
		x=0;
		y=0;
		val=0;
	}
	public Solution(float x, float y, double val) {
		super();
		this.x = x;
		this.y = y;
		this.val = val;
	}

	public float y;
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public double getVal() {
		return val;
	}

	public double val;
	
	public void set(float X, float Y, double Val){
		x=X;
		y=Y;
		val = Val;
	}

}
