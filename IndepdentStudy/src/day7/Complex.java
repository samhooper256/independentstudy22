package day7;

import java.util.Objects;

public final class Complex {

	public static final Complex ZERO = new Complex(0, 0);
	
	private final double real, imaginary;
	
	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public double real() {
		return real;
	}
	
	public double imaginary() {
		return imaginary;
	}
	
	public boolean isReal() {
		return imaginary() == 0;
	}
	
	public Complex add(Complex x) {
		return new Complex(real() + x.real(), imaginary() + x.imaginary());
	}

	public Complex subtract(Complex x) {
		return new Complex(real() - x.real(), imaginary() - x.imaginary());
	}
	
	public Complex negate() {
		return new Complex(-real(), -imaginary());
	}
	
	public Complex multiply(Complex x) {
		double a = real(), b = imaginary(), c = x.real(), d = x.imaginary();
		double s1 = a * c, s2 = b * d, s3 = (a + b) * (c + d);
		return new Complex(s1 - s2, s3 - s1 - s2);
	}
	
	public Complex divide(Complex x) {
		if(isReal() && x.isReal()) //if we're just dividing real numbers
			return new Complex(real() / x.real, 0);
		Complex num = multiply(x.conjuagte());
		double div = x.real() * x.real() + x.imaginary() * x.imaginary();
		return new Complex(num.real() / div, num.imaginary() / div);
	}
	
	public Complex conjuagte() {
		if(isReal())
			return this;
		return new Complex(real(), -imaginary());
	}
	
	@Override
	public String toString() {
		return String.format("%s%s%si", real(), imaginary() >= 0 ? "+" : "", imaginary());
	}

	@Override
	public int hashCode() {
		return Objects.hash(real(), imaginary());
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof Complex))
			return false;
		Complex other = (Complex) obj;
		return real() == other.real() && imaginary() == other.imaginary();
	}
	
}
