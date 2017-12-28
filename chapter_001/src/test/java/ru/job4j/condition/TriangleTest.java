package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        // Create 3 points.
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);

        // Create triangle with our points.
        Triangle triangle = new Triangle(a, b, c);
        // Use check method.
        double result = triangle.area();
        // Check result and expected value.
        assertThat(result, closeTo(2d, 0.1D));
    }

    @Test
    public void whenPeriodSetThreeLineThenSemiPerimeter() {
        // Create 3 points.
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);

        // Create new Triangle
        Triangle triangle = new Triangle(a, b, c);

        // Calculate distance.
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        // Calculate semi-perimeter.
        double result = triangle.period(ab, ac, bc);
        // Check result and expected value.
        assertThat(result, closeTo(3.414213562373095, 0.1));
    }
}
