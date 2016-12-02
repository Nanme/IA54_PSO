
public class FunctionRastrigin implements FunctionPSO {

	@Override
	public double applyAsDouble(Float x, Float y) {


		return Math.pow(x,2)-10*Math.cos(2*Math.PI*x)+Math.pow(y,2)-10*Math.cos(2*Math.PI*y);
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
	@Override
	public String toString(){
		return "x^2-10*cos(2*pi*x)+y^2-10*cos(2*pi*y)+20";
	}

}
