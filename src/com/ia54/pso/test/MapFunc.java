import java.util.Vector;

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
		calc(fonctionMap);
	}
	MapFunc(int width, int height,FunctionPSO fonctionMap, float[] d){
		
		z = new double[width*height];
		this.width = width;
		this.height = height;
		calc(fonctionMap,d);
	}
	MapFunc(int width, int height,FunctionPSO fonctionMap,float xrange,float Xrange, float yrange, float Yrange){
		
		z = new double[width*height];
		this.width = width;
		this.height = height;
		calc(fonctionMap, xrange, Xrange, yrange, Yrange);
	}
	
	public void negatif(){
		for (int i = 0; i<width*height; ++i)
		{
			z[i] -= Min.val;
			z[i] -= (Max.val-Min.val)/2;
			z[i] *= -1;
			z[i] += Min.val + (Max.val-Min.val)/2;
		}
	}
	
	public void fusion(MapFunc mp)
	{
		fusion(mp, 1);
	}
	
	public void fusion(MapFunc mp, double Poids)
	{
		Function.addAll(mp.Function);
		if (mp.height == height && mp.width == width)
		{
			double k = (Max.val-Min.val) / (mp.Max.val - mp.Min.val)*Poids;
			Max.val = z[0] + mp.z[0]*k;
			Min.val = Max.val;
			for (int i = 0; i<width*height; ++i)
			{
				z[i] += mp.z[i]*k;
				if(z[i] < Min.val)
					Min.set(Math.floorDiv(i, width), i%width, z[i]);
				else if (z[i] > Max.val)
					Max.set(Math.floorDiv(i, width), i%width, z[i]);
			}
				
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
    	calc(fonctionMap, xrange,Xrange, yrange, Yrange, Poids);
    }
    
    public void addF(FunctionPSO fonctionMap, float[] d,double Poids){
    	calc(fonctionMap, d, Poids);
    }
    public double[] calc(FunctionPSO fonctionMap)
	{
    	return calc(fonctionMap, fonctionMap.interval());
	}

    public double[] calc(FunctionPSO fonctionMap, float[] d)
	{
    	return calc(fonctionMap, d[0],d[1],d[2], d[3]);
	}
    
    private double[] calc(FunctionPSO fonctionMap, float[] d, double Poids)
 	{
     	return calc(fonctionMap, d[0],d[1],d[2], d[3],Poids);
 	}

    public double[] calc(FunctionPSO fonctionMap,float xrange,float Xrange, float yrange, float Yrange)
    {
    	return calc(fonctionMap, xrange,Xrange, yrange, Yrange,1);
    }
    
    
	private double[] calc(FunctionPSO fonctionMap,float xrange,float Xrange, float yrange, float Yrange,double Poids)
	{	
		Function.add(new funcParam(fonctionMap, xrange, Xrange, yrange, Yrange ,  Poids));
		Max.val = fonctionMap.applyAsDouble(Xrange,Yrange);
		Min.val = fonctionMap.applyAsDouble(Xrange,Yrange);

		//int couleurNeutre = 0xFF_7F_7F_7F;
		float x = xrange;
		float y = yrange;
		
		int i = 0;
		for (int u =0; u<width;  ++u )
		{
			x += (Xrange-xrange)/width;
			for(int v = 0; v<height; ++v)
			{
				if(v == 0)
					y = yrange;
				else
					y += (Yrange-yrange)/height;
				z[i] += fonctionMap.applyAsDouble(x,y)*Poids;
				
				if(z[i] < Min.val)
					Min.set(u, v, z[i]);
				else if (z[i] > Max.val)
					Max.set(u, v, z[i]);
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
		
		int Pg = 0x1_00, Pr = 0x1_00_00; //Pg : puissance pour atteindre le vert/green, Pr pour le rouge
		for(int i = 0; i<NBPIX; ++i)
		{
			
			z[i] = Math.round(k*(z[i]-Min.val)); // on passe dans un environnement [0..255]
			if(z[i]>255 || z[i]<0)
				System.out.println(z[i]);
			pixels[i] =(int)(0xFF_00_00_00 + z[i]*Pr + z[i]*Pg + z[i]); 
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
//		funcParam(FunctionPSO f, float[] range, double poids)
//		{
//			func = f;
//			this.range = range;
//			Poids = poids;
//		}
		
		public funcParam(FunctionPSO fonctionMap, float xrange, float xrange2, float yrange, float yrange2,
				double poids) {
			func = fonctionMap;
			Poids = poids;
			range = new float[]{xrange, xrange2, yrange, yrange2};
			
		}
	}
 
}

