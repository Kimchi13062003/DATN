package MyCalculator_String;

public class Calculator_New {
	
	public int add(int a, int b) {
        return a + b;
    }

    public double add2(double a, double b) {
        return a + b;
    }

    public int multiply(int a, int b, int c) {
        return a * b * c;
    }

    public int sumArray(int[] numbers) {
        int sum = 0;
        for (int n : numbers) sum += n;
        return sum;
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
