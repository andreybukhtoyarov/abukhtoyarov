package ru.job4j.shapes;

/**
 * This class is Context class for shapes.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Paint {
    private Shape shape;

    Paint(Shape shape) {
        this.shape = shape;
    }

    public void draw() {
        System.out.print(this.shape.pic());
    }

    public static void main(String[] args) {
        Paint paint = new Paint(new Triangle());
        paint.draw();
        System.out.println();
        System.out.println();
        paint = new Paint(new Square());
        paint.draw();
    }
}
