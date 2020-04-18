package main.snippets.algo.queueingmodels;
public class MMs {
	
	public static double factorial(double n) {
		double result = 1;
		for (int i = 1; i <= n; i++) {
			result = result * i;
		}
		return result;
	}
	
	public static double calcP0(double n, double s, double lambda, double mu) {
		// Calculate sum.
		double sum = 0;
		for (int i = 0; i <= s - 1; i++) {
			sum += Math.pow((lambda / mu), i) / factorial(i);
		}
		
		double rho = lambda / (s * mu);
		
		// Calculate second member.
		double secondMemb = (Math.pow((lambda / mu), s) / factorial(s)) * (1 / (1 - rho));
		
		// Return
		return 1 / (sum + secondMemb);
	}
	
	public static double calcPn(double p0, double n, double s, double lambda, double mu) {
		// Calculate Cn'
		double cn = 0;
		if (n <= s) {
			cn = Math.pow(lambda / mu, n) / factorial(n);
		} else if (n > s) {
			cn = Math.pow(lambda / mu, n) / (Math.pow(s, n - s) * factorial(s));
		}
		return cn * p0;
	}
	
	public static void main(String[] args) {
		
		double n = 4;
		double s = 2;
		double lambda = 90;
		double mu = 120;
		double p0 = 0;
		

		p0 = calcP0(n, s, lambda, mu);
		
		double probability = p0;

		
		for (int i = 1; i <= n; i++) {
			probability += calcPn(p0, i, s, lambda, mu);
		}
		
//		double probability = factorial(n);
		
		System.out.println(probability);
		
	}
}
