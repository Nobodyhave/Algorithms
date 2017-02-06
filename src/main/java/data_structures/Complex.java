package data_structures;

import java.util.Objects;

public class Complex {
    private final double real;
    private final double imaginary;

    /**
     * Creates new complex number
     *
     * @param real      part
     * @param imaginary part
     */
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

    /**
     * @return hypotenuse of complex number
     */
    public double abs() {
        return Math.hypot(real, imaginary);
    }


    /**
     * @return phase of complex number
     */
    public double phase() {
        return Math.atan2(imaginary, real);
    }

    /**
     * @param b complex number
     * @return sum of current complex number with b
     */
    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.real + b.real;
        double imaginary = a.imaginary + b.imaginary;
        return new Complex(real, imaginary);
    }

    /**
     * @param b complex number
     * @return difference of current complex number with b
     */
    public Complex subtract(Complex b) {
        Complex a = this;
        double real = a.real - b.real;
        double imaginary = a.imaginary - b.imaginary;
        return new Complex(real, imaginary);
    }

    /**
     * @param b complex number
     * @return product of current complex number with b
     */
    public Complex multiply(Complex b) {
        Complex a = this;
        double real = a.real * b.real - a.imaginary * b.imaginary;
        double imaginary = a.real * b.imaginary + a.imaginary * b.real;
        return new Complex(real, imaginary);
    }

    /**
     * @param alpha complex number
     * @return scaled complex number
     */
    public Complex scale(double alpha) {
        return new Complex(alpha * real, alpha * imaginary);
    }

    /**
     * @return conjugation of current complex number
     */
    public Complex conjugate() {
        return new Complex(real, -imaginary);
    }

    /**
     * @return reciprocal of current complex number
     */
    public Complex reciprocal() {
        double scale = real * real + imaginary * imaginary;
        return new Complex(real / scale, -imaginary / scale);
    }

    /**
     * @return real part of complex number
     */
    public double real() {
        return real;
    }

    /**
     * @return imaginary part of complex number
     */
    public double imaginary() {
        return imaginary;
    }

    /**
     * @param b complex number
     * @return division of complex number by b
     */
    public Complex divide(Complex b) {
        Complex a = this;
        return a.multiply(b.reciprocal());
    }

    /**
     * @return exponential of complex number
     */
    public Complex exp() {
        return new Complex(Math.exp(real) * Math.cos(imaginary), Math.exp(real) * Math.sin(imaginary));
    }

    /**
     * @return complex sine of complex number
     */
    public Complex sin() {
        return new Complex(Math.sin(real) * Math.cosh(imaginary), Math.cos(real) * Math.sinh(imaginary));
    }

    /**
     * @return complex cosine of complex number
     */
    public Complex cos() {
        return new Complex(Math.cos(real) * Math.cosh(imaginary), -Math.sin(real) * Math.sinh(imaginary));
    }

    /**
     * @return complex tangent of complex number
     */
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
