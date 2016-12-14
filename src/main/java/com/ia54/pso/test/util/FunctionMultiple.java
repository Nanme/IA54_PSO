package main.java.com.ia54.pso.test.util;

import java.util.Vector;


public class FunctionMultiple implements FunctionPSO {


	private float height,width;
	
	
	public Vector<FuncParam> Function = new Vector<FuncParam>();
	
	public FunctionMultiple(float[] d)
	{
		this.height =  Math.abs(d[1] - d[0]);
		this.width =  Math.abs(d[3] - d[2]);
	}
	
	public FunctionMultiple(float x1, float x2, float y1, float y2)
	{
		this.height =  Math.abs(x2 - x1);
		this.width =  Math.abs(y2 - y1);
	}
	
	
	public FunctionMultiple(float height, float width)
	{
		this.height = height;
		this.width = width;
	}
	
	
	public double getValue(Float x, Float y) {
		float X,Y;
		double res = 0;
		for(FuncParam i:Function)
		{
			X = i.range[0] + (x/(width-1))*(i.range[1]-i.range[0]);
			Y = i.range[2] + (y/(height-1))*(i.range[3]-i.range[2]);
			res += i.func.applyAsDouble(X, Y) * i.Poids;
		}
		return res;
	}
	
    public void add(FunctionPSO fonctionMap, float[] d){
    	Function.add(new FuncParam(fonctionMap, d ,  1));
    }
	
	public void add(FunctionPSO fonctionMap){
	    	Function.add(new FuncParam(fonctionMap, fonctionMap.interval() ,  1));
	}
	
    public void add(FunctionPSO fonctionMap, float[] d,double Poids){
    	Function.add(new FuncParam(fonctionMap, d ,  Poids));
    }
    
    public void add(FunctionPSO fonctionMap,float xrange,float Xrange, float yrange, float Yrange,double Poids){
    	Function.add(new FuncParam(fonctionMap, xrange, Xrange, yrange, Yrange ,  Poids));
    }
    public void add(FunctionPSO fonctionMap,float xrange,float Xrange, float yrange, float Yrange){
    	Function.add(new FuncParam(fonctionMap, xrange, Xrange, yrange, Yrange ,  1));
    }
    
    
	public void add(FuncParam f)
	{
		Function.add(f);
	}
	
	public void fusion(FunctionMultiple mp, double Poids)
	{
		for(FuncParam i:mp.Function) {
			
			Function.add(i.clone());
			Function.lastElement().Poids *=Poids;
		}
	}
	
	public void negatif(){

		for (FuncParam i:Function)
			i.Poids *= -1;
	}

	@Override
	public double applyAsDouble(Float x, Float y) {
		return getValue(x,y);
	}

	@Override
	public float[] interval() {
		return new float[]{0,600,0,600};
	}

	@Override
	public float[] interval2() {
		return null;
	}
	
	@Override
	public String toString()
	{
		return "heu .. blblbl ?";
	}
	
	private class FuncParam {
		
		public FunctionPSO func;
		public float[] range;
		public double Poids;
		
		private FuncParam(FunctionPSO f, float[] range, double poids)
		{
			func = f;
			this.range = range;
			Poids = poids;
		}
		
		public FuncParam(FunctionPSO fonctionMap, float xrange, float xrange2, float yrange, float yrange2,
				double poids) {
			func = fonctionMap;
			Poids = poids;
			range = new float[]{xrange, xrange2, yrange, yrange2};
			
		}
		
		public FuncParam clone(){
			return new FuncParam(func,range,Poids);
		}
	}

}
