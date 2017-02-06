package shortcuts;

import java.util.Objects;


@SuppressWarnings("unused")
public class Complex {
    private final double real;
    private final double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {
        if (imaginary == 0) return real + "";
        if (real == 0) return imaginary + "i";
        if (imaginary < 0) return real + " - " + (-imaginary) + "i";
        return real + " + " + imaginary + "i";
    }

    public double abs() {
        return Math.hypot(real, imaginary);
    }

    public double phase() {
        return Math.atan2(imaginary, real);
    }

    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.real + b.real;
        double imaginary = a.imaginary + b.imaginary;
        return new Complex(real, imaginary);
    }

    public Complex subtract(Complex b) {
        Complex a = this;
        double real = a.real - b.real;
        double imaginary = a.imaginary - b.imaginary;
        return new Complex(real, imaginary);
    }

    public Complex multiply(Complex b) {
        Complex a = this;
        double real = a.real * b.real - a.imaginary * b.imaginary;
        double imaginary = a.real * b.imaginary + a.imaginary * b.real;
        return new Complex(real, imaginary);
    }

    public Complex scale(double alpha) {
        return new Complex(alpha * real, alpha * imaginary);
    }

    public Complex conjugate() {
        return new Complex(real, -imaginary);
    }

    public Complex reciprocal() {
        double scale = real * real + imaginary * imaginary;
        return new Complex(real / scale, -imaginary / scale);
    }

    public double real() {
        return real;
    }

    public double imaginary() {
        return imaginary;
    }

    public Complex divide(Complex b) {
        Complex a = this;
        return a.multiply(b.reciprocal());
    }

    public Complex exp() {
        return new Complex(Math.exp(real) * Math.cos(imaginary), Math.exp(real) * Math.sin(imaginary));
    }

    public Complex sin() {
        return new Complex(Math.sin(real) * Math.cosh(imaginary), Math.cos(real) * Math.sinh(imaginary));
    }

    public Complex cos() {
        return new Complex(Math.cos(real) * Math.cosh(imaginary), -Math.sin(real) * Math.sinh(imaginary));
    }

    public Complex tan() {
        return sin().divide(cos());
    }

    @Override
    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Complex that = (Complex) x;
        return (this.real == that.real) && (this.imaginary == that.imaginary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }
}
