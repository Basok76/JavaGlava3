package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RationalFraction {
    private int numerator;
    private int denominator;

    public RationalFraction(int numerator, int denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Denominator cannot be zero");
        int gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    public RationalFraction add(RationalFraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalFraction(newNumerator, newDenominator);
    }

    public RationalFraction subtract(RationalFraction other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalFraction(newNumerator, newDenominator);
    }

    public RationalFraction multiply(RationalFraction other) {
        return new RationalFraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    public RationalFraction divide(RationalFraction other) {
        if (other.numerator == 0) throw new ArithmeticException("Division by zero");
        return new RationalFraction(this.numerator * other.denominator, this.denominator * other.numerator);
    }

    public boolean equals(RationalFraction other) {
        return this.numerator == other.numerator && this.denominator == other.denominator;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }
}

class Line {
    private RationalFraction a;
    private RationalFraction b;
    private RationalFraction c;

    public Line(RationalFraction a, RationalFraction b, RationalFraction c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public RationalFraction[] intersectionWithXAxis() {
        if (b.equals(new RationalFraction(0, 1))) return null;
        RationalFraction x = c.divide(a).multiply(new RationalFraction(-1, 1));
        return new RationalFraction[]{x, new RationalFraction(0, 1)};
    }

    public RationalFraction[] intersectionWithYAxis() {
        if (a.equals(new RationalFraction(0, 1))) return null;
        RationalFraction y = c.divide(b).multiply(new RationalFraction(-1, 1));
        return new RationalFraction[]{new RationalFraction(0, 1), y};
    }

    public RationalFraction[] intersectionWith(Line other) {
        RationalFraction determinant = a.multiply(other.b).subtract(b.multiply(other.a));
        if (determinant.equals(new RationalFraction(0, 1))) return null;

        RationalFraction x = c.multiply(other.b).subtract(b.multiply(other.c)).divide(determinant).multiply(new RationalFraction(-1, 1));
        RationalFraction y = a.multiply(other.c).subtract(c.multiply(other.a)).divide(determinant).multiply(new RationalFraction(-1, 1));
        return new RationalFraction[]{x, y};
    }

    public boolean isParallel(Line other) {
        return a.multiply(other.b).equals(b.multiply(other.a));
    }

    public String toString() {
        return a + "*x + " + b + "*y + " + c + " = 0";
    }
}

public class C2 {
    public static void main(String[] args) {

        Line l1 = new Line(new RationalFraction(1, 1), new RationalFraction(-1, 1), new RationalFraction(-3, 1));
        Line l2 = new Line(new RationalFraction(2, 1), new RationalFraction(-2, 1), new RationalFraction(1, 1));
        Line l3 = new Line(new RationalFraction(1, 1), new RationalFraction(-1, 1), new RationalFraction(4, 1));
        Line l4 = new Line(new RationalFraction(1, 1), new RationalFraction(2, 1), new RationalFraction(-5, 1));

        List<Line> lines = List.of(l1, l2, l3, l4);

        Map<String, List<Line>> parallelGroups = new HashMap<>();
        for (Line line1 : lines) {
            String groupKey = line1.toString();
            parallelGroups.putIfAbsent(groupKey, new ArrayList<>());
            for (Line line2 : lines) {
                if (line1 != line2 && line1.isParallel(line2)) {
                    parallelGroups.get(groupKey).add(line2);
                }
            }
        }

        for (Line line : lines) {
            System.out.println("Прямая: " + line);
            RationalFraction[] xIntersection = line.intersectionWithXAxis();
            RationalFraction[] yIntersection = line.intersectionWithYAxis();
            System.out.println("  Пересечение с осью X: " + (xIntersection != null ? "(" + xIntersection[0] + ", " + xIntersection[1] + ")" : "нет"));
            System.out.println("  Пересечение с осью Y: " + (yIntersection != null ? "(" + yIntersection[0] + ", " + yIntersection[1] + ")" : "нет"));
        }

        System.out.println("\nГруппы параллельных прямых:");
        for (Map.Entry<String, List<Line>> entry : parallelGroups.entrySet()) {
            System.out.println("Прямая " + entry.getKey() + " параллельна: " + entry.getValue());
        }
    }
}
