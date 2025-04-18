package Math;

import java.util.Iterator;

public class MathUtils {
	public int add(int a, int b) {
		return a + b;
	}
	
	public int factorial(int n) {
		if (n < 0) throw new IllegalArgumentException("n must be >= 0");
		int result = 1;
		for (int i = 2; i <= n; i ++)
			result *= i;
		return result;
	}
	
	private boolean isEven(int n) {
		return n % 2 == 0; 
	}
}
