package ru.job4j.shapes;

/**
 * This class is interface and Strategy for shapes.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface Shape {
    /**
     * This method must return a shape in a pseudo-graphic in the form of a line.
     * @return line with a shape in a pseudo-graphic.
     */
    String pic();
}
