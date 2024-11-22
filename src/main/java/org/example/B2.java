package org.example;
import java.util.ArrayList;
import java.util.List;

class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    public Complex subtract(Complex other) {
        return new Complex(this.real - other.real, this.imaginary - other.imaginary);
    }

    public Complex multiply(Complex other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new Complex(newReal, newImaginary);
    }

    public Complex divide(Complex other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double newReal = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double newImaginary = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
        return new Complex(newReal, newImaginary);
    }

    @Override
    public String toString() {
        return String.format("%.2f + %.2fi", real, imaginary);
    }
}

class ComplexOperations {

    public static Complex sumElements(List<Complex> complexList) {
        Complex result = new Complex(0, 0);
        for (Complex complex : complexList) {
            result = result.add(complex);
        }
        return result;
    }

    public static Complex multiplyElements(List<Complex> complexList) {
        Complex result = new Complex(1, 0); // Единичный элемент
        for (Complex complex : complexList) {
            result = result.multiply(complex);
        }
        return result;
    }

    public static void main(String[] args) {

        List<Complex> complexList = new ArrayList<>();
        complexList.add(new Complex(3, 2));
        complexList.add(new Complex(3, 4));
        complexList.add(new Complex(-1, -1));

        Complex sumResult = sumElements(complexList);
        System.out.println("Сумма элементов: " + sumResult);

        Complex multiplyResult = multiplyElements(complexList);
        System.out.println("Произведение элементов: " + multiplyResult);
    }
}
