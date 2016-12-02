import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;



public class MapFunc {
	int width;
	int height;
	public double Min = 1, Max = 2;
	public double z[];
	public WritableImage img;
	
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
			z[i] -= Min;
			z[i] -= (Max-Min)/2;
			z[i] *= -1;
			z[i] += Min + (Max-Min)/2;
		}
	}
	
	public void fusion(MapFunc mp)
	{
		fusion(mp, 1);
	}
	
	public void fusion(MapFunc mp, double Poids)
	{
		if (mp.height == height && mp.width == width)
		{
			double k = (Max-Min) / (mp.Max - mp.Min)*Poids;
			for (int i = 0; i<width*height; ++i)
				z[i] += mp.z[i]*k;
			
			Max += mp.Max*k;
			Min += mp.Min*k;
				
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
		
		Max = fonctionMap.applyAsDouble(Xrange,Yrange);
		Min = fonctionMap.applyAsDouble(Xrange,Yrange);

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
				
				if(z[i] < Min)
					Min = z[i];
				else if (z[i] > Max)
					Max = z[i];
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
		
		double k = 255/(Max-Min)*contrast; //facteur correcteur
		
		int Pg = 0x1_00, Pr = 0x1_00_00; //Pg : puissance pour atteindre le vert/green, Pr pour le rouge
		for(int i = 0; i<NBPIX; ++i)
		{
			
			z[i] = Math.round(k*(z[i]-Min)); // on passe dans un environnement [0..255]
			if(z[i]>255 || z[i]<0)
				System.out.println(z[i]);
			pixels[i] =(int)(0xFF_00_00_00 + z[i]*Pr + z[i]*Pg + z[i]); 
								   // A			R		G		   B  : définition des couleurs ARGB
			if (pixels[i] >0) pixels[i] = 0xFF_FF_FF_FF;
			
		}						
		
		
		img = new WritableImage(width, height);
		PixelWriter pw = img.getPixelWriter();
		pw.setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pixels, 0, width);
	
		
		return img;
		//final ImageView imv = new ImageView();
	}
 
}
