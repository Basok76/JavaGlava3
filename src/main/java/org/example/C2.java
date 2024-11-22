package org.example;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Rational {
    double numerator;
    double denominator;

    public Rational(double numerator, double denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Перевод в double
    public double toDouble() {
        return numerator / denominator;
    }
}

class StraightLine {
    double A, B, C;

    public StraightLine(double A, double B, double C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public double intersectWithX() {
        return C / A;
    }

    public double intersectWithY() {
        return C / B;
    }

    public Point intersect(StraightLine other) {
        double det = A * other.B - B * other.A;
        if (det == 0) {
            return null;
        }
        double x = (C * other.B - B * other.C) / det;
        double y = (A * other.C - C * other.A) / det;
        return new Point(x, y);
    }

    public boolean isParallel(StraightLine other) {
        return A * other.B == B * other.A;
    }
}

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public class C2 {
    public static void main(String[] args) {
        List<StraightLine> lines = new ArrayList<>();
        lines.add(new StraightLine(2, -3, 6));  // Прямая 2x - 3y = 6
        lines.add(new StraightLine(1, -1, 2));  // Прямая x - y = 2
        lines.add(new StraightLine(2, -3, 3));  // Прямая 2x - 3y = 3

        for (StraightLine line : lines) {
            System.out.println("Прямая " + line.A + "x + " + line.B + "y = " + line.C);
            System.out.println("Пересечение с осью X: " + line.intersectWithX());
            System.out.println("Пересечение с осью Y: " + line.intersectWithY());
        }

        StraightLine line1 = lines.get(0);
        StraightLine line2 = lines.get(1);
        Point intersection = line1.intersect(line2);
        if (intersection != null) {
            System.out.println("Пересечение двух прямых: " + intersection);
        } else {
            System.out.println("Прямые параллельны");
        }

        Set<Set<StraightLine>> parallelGroups = groupParallelLines(lines);
        System.out.println("Группы параллельных прямых:");
        for (Set<StraightLine> group : parallelGroups) {
            System.out.print("Группа: ");
            for (StraightLine l : group) {
                System.out.print(" (" + l.A + "x + " + l.B + "y = " + l.C + ")");
            }
            System.out.println();
        }
    }

    public static Set<Set<StraightLine>> groupParallelLines(List<StraightLine> lines) {
        Set<Set<StraightLine>> parallelGroups = new HashSet<>();
        for (StraightLine line : lines) {
            boolean added = false;
            for (Set<StraightLine> group : parallelGroups) {
                for (StraightLine l : group) {
                    if (line.isParallel(l)) {
                        group.add(line);
                        added = true;
                        break;
                    }
                }
                if (added) break;
            }
            if (!added) {
                Set<StraightLine> newGroup = new HashSet<>();
                newGroup.add(line);
                parallelGroups.add(newGroup);
            }
        }
        return parallelGroups;
    }
}
