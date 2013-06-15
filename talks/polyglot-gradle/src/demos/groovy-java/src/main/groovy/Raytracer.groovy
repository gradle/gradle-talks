public class Raytracer {
	private long result = 0;
	
	public String computeImage() {
		for (long i = 0; i < 3333333333L; i++) {
			traceA();
		}
		return Util.format(result);
	}

	private void traceA() {
		traceB();
	}
	
	private void traceB() {
	  traceC();
	}
	
	private void traceC() {
	  result++;
	}
}