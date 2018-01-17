package ru.job4j.shapes;

import java.util.StringJoiner;

/**
 * This class describe Triangle.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Triangle implements Shape {
    /**
     * This method return a triangle in a pseudo-graphic in the form of a line.
     * @return line with a triangle in a pseudo-graphic.
     */
    @Override
    public String pic() {
        return new StringJoiner(System.lineSeparator())
                .add("*")
                .add("**")
                .add("***")
                .add("****")
                .add("*****")
                .toString();
    }
}
