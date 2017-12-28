package ru.job4j.condition;

/**
 * This class describe triangle.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @since 28.12.2017.
 * @version 1.0.
 */
public class Triangle {
    /** Field point a, b and c. */
    private Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * Formula - P = (ab + ac + bc) / 2.
     *
     * @param ab - length between a and b.
     * @param ac - length between a and c.
     * @param bc - length between b and c.
     * @return Semi-perimeter.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * This method calculates area of triangle.
     *
     * Formula - S = (p * (p - a) * (p - b) * (p - c))^1/2.
     *
     * @return area of triangle if triangle is exist and -1 if triangle is no exist.
     */
    public double area() {
        double result = -1;

        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = period(ab, ac, bc);

        if (exist(ab, ac, bc)) {
            result = Math.sqrt(
                    p * (p - ab) * (p - ac) * (p - bc)
            );
        }

        return result;
    }

    /**
     * This method check is exist triangle.
     * @param ab - length between a and b.
     * @param ac - length between a and c.
     * @param bc - length between b and c.
     * @return true if triangle exist.
     */
    private boolean exist(double ab, double ac, double bc) {
        return ab != 0 && ac != 0 && bc != 0;
    }
}
