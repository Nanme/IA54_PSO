import java.util.function.ToDoubleBiFunction;

public class FunctionNocon_rastrigin implements FunctionPSO {
private static double z = 0;
	@Override
	public double applyAsDouble(Float x, Float y) {
		float temp1, temp2;
		if (Math.abs(x)<1/2)
			temp1=x;
		else
			temp1=Math.round(2.*x)/2;
		if (Math.abs(y)<1/2)
			temp2=y;
		else
			temp2=Math.round(2.*y)/2;
	
	
		
		return Math.pow(temp1,2)-10*Math.cos(2*Math.PI*temp1)+Math.pow(temp2,2)-10*Math.cos(2*Math.PI*temp2)+20;
	}
	@Override
	public String toString(){
		return "No Continue Rastrigin";
	}

	@Override
	public float[] interval() {
		float d[] = {-10,10,-10,10};
		return d;
		
	}

	@Override
	public float[] interval2() {
		// TODO Auto-generated method stub
		return null;
	}
}
