package ru.job4j.condition;

/**
 *This class describe point in system coordinate.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version 1.0.
 *@since 25.12.2017.
 */
public class Point {
    /** Field x and y coordinate. */
    private int x, y;

    /**
     * This is constructor of class Point.
     * @param x - coordinate x.
     * @param y - coordinate y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *This method calculates distance between two points.
     *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     *return distance.
     *param that - argument of type Point.
     */
    public double distanceTo(Point that) {
        Point a = this;
        Point b = that;

        int x1 = a.x;
        int y1 = a.y;
        int x2 = b.x;
        int y2 = b.y;

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        Point a = new Point(-1, 9);
        Point b = new Point(3, 0);

        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);

        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками a и b = " + result);
    }
}
