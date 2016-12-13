package com.ia54.pso.test;

import java.util.Vector;

import com.ia54.pso.test.util.FunctionNoise;
import com.ia54.pso.test.util.FunctionPSO;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;



public class MapFunc {
	int width;
	int height;
	public Solution Min = new Solution(), Max = new Solution();
	private double z[];
	public WritableImage img;
	Vector<funcParam> Function = new Vector<funcParam>();
	
	
	
	public double getValue(float x,float y)
	{
		float X,Y;
		double res = 0;
		for(funcParam i:Function)
		{
			X = i.range[0] + (x/(width-1))*(i.range[1]-i.range[0]);
			Y = i.range[2] + (y/(height-1))*(i.range[3]-i.range[2]);
			res += i.func.applyAsDouble(X, Y) * i.Poids;
		}
		return res;
	}
//	
//	public void setValue(double val, int x, int y)
//	{
//		z[x*width+y] = val;
//			if(val < Min.val)
//				Min.set(x,y,val);
//			else if (val > Max.val)
//				Max.set(x,y,val);
//		
//	}
	
	MapFunc(int width, int height){
		
		z = new double[width*height];
		this.width = width;
		this.height = height;
	}
	MapFunc(int width, int height,FunctionPSO fonctionMap){
		
		z = new double[width*height];
		this.width = width;
		this.height = height;
		addF(fonctionMap);
	}
	MapFunc(int width, int height,FunctionPSO fonctionMap, float[] d){
		
		z = new double[width*height];
		this.width = width;
		this.height = height;
		addF(fonctionMap,d);
	}
	MapFunc(int width, int height,FunctionPSO fonctionMap,float xrange,float Xrange, float yrange, float Yrange){
		
		z = new double[width*height];
		this.width = width;
		this.height = height;
		addF(fonctionMap, xrange, Xrange, yrange, Yrange);
	}
	
	public void negatif(){

		for (funcParam i:Function)
			i.Poids *= -1;
		calc();
	}
	
	public void fusion(MapFunc mp)
	{
		fusion(mp, 1);
	}
	
	public void fusion(MapFunc mp, double Poids)
	{
		
		if (mp.height == height && mp.width == width)
		{
			
			double k = (Max.val-Min.val) / (mp.Max.val - mp.Min.val)*Poids;
			 
			for(funcParam i:mp.Function) {
				
				Function.add(i.clone());
				Function.lastElement().Poids *=k;
			}
			calc();
		
		}
	}

	public void noise()
	{
		double heavy = (0.4 + (Math.random()*0.8));
		noise(heavy);
	}
	public void noise(double heavy)
	{
		float frequency = (float) (60 + (Math.random()*240));
		noise(frequency, heavy);
	}
	
	public void noise(float frequency, double heavy)
	{
		
		float bandwidthX =  150*width/height, bandwidthY = 150*height/width;
		float BMinX = (float) (-300 + (Math.random()*600)),BMinY = (float) (-300 + (Math.random()*600));
		
		FunctionNoise noise = new FunctionNoise(frequency);
		MapFunc mp = new MapFunc(width, height, noise, BMinX, BMinX + bandwidthX, BMinY, BMinY + bandwidthY);
			
		fusion(mp,heavy);
	}
    
    public void addF(FunctionPSO fonctionMap,float xrange,float Xrange, float yrange, float Yrange,double Poids){
    	Function.add(new funcParam(fonctionMap, xrange, Xrange, yrange, Yrange ,  Poids));
    	calc();
    	
    }
    public void addF(FunctionPSO fonctionMap,float xrange,float Xrange, float yrange, float Yrange){
    	Function.add(new funcParam(fonctionMap, xrange, Xrange, yrange, Yrange ,  1));
    	calc();
    	
    }
    
    public void addF(FunctionPSO fonctionMap, float[] d,double Poids){
    	Function.add(new funcParam(fonctionMap, d ,  Poids));
    	calc();
    }
    public void addF(FunctionPSO fonctionMap, float[] d){
    	Function.add(new funcParam(fonctionMap, d ,  1));
    	calc();
    }

    public void addF(FunctionPSO fonctionMap){
    	Function.add(new funcParam(fonctionMap, fonctionMap.interval() ,  1));
    	calc();
    }
    
    
	private double[] calc()
	{	
		
		Max.val = getValue(0,0);
		Min.val = getValue(0,0);

		//int couleurNeutre = 0xFF_7F_7F_7F;
		
		int i = 0;
		for (int x =0; x<width;  ++x )
		{
			for(int y = 0; y<height; ++y)
			{
				z[i] = getValue(x,y);
				
				if(z[i] < Min.val)
					Min.set(x, y, z[i]);
				else if (z[i] > Max.val)
					Max.set(x, y, z[i]);
				++i;
			}
		}
		return z;
	}
	
				
	public Image draw()
	{
		return draw(1);
	}
				
	public Image draw(double contrast)
	{System.out.print(0xFF_00_00_00);
		int NBPIX = width*height;
		int[] pixels = new int[NBPIX] ;
		
		double k = 255/(Max.val-Min.val)*contrast; //facteur correcteur
		double[] Z = new double[NBPIX];
		
		int Pg = 0x1_00, Pr = 0x1_00_00; //Pg : puissance pour atteindre le vert/green, Pr pour le rouge
		for(int i = 0; i<NBPIX; ++i)
		{
			
			Z[i] = Math.round(k*(z[i]-Min.val)); // on passe dans un environnement [0..255]
			if(Z[i]>255 || Z[i]<0)
				System.out.println(Z[i]);
			pixels[i] =(int)(0xFF_00_00_00 + Z[i]*Pr + Z[i]*Pg + Z[i]); 
								   // A			R		G		   B  : définition des couleurs ARGB
			if (pixels[i] >0) pixels[i] = 0xFF_FF_FF_FF;
			//pixels[(int) (Math.floor(Max.x)*width+Math.floor(Max.y))] = 0xFF_FF_00_00;
			
		}						
		
		
		img = new WritableImage(width, height);
		PixelWriter pw = img.getPixelWriter();
		pw.setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pixels, 0, width);
	
		
		return img;
		//final ImageView imv = new ImageView();
	}
	
	private class funcParam {
		public FunctionPSO func;
		public float[] range;
		public double Poids;
		funcParam(FunctionPSO f, float[] range, double poids)
		{
			func = f;
			this.range = range;
			Poids = poids;
		}
		
		public funcParam(FunctionPSO fonctionMap, float xrange, float xrange2, float yrange, float yrange2,
				double poids) {
			func = fonctionMap;
			Poids = poids;
			range = new float[]{xrange, xrange2, yrange, yrange2};
			
		}
		
		public funcParam clone(){
			return new funcParam(func,range,Poids);
		}
	}
 
}

