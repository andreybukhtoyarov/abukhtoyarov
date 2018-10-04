package ru.job4j.chessboard;

import java.util.function.BiFunction;

/**
 * This class.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Position {
    /**
     * Return BiFunction for position figure by X Cell.
     * @param source start position
     * @param dest end position
     * @param fun BiFunction
     * @return Return BiFunction for position figure by X Cell.
     */
    int getPositionX(Cell source, Cell dest, BiFunction<Integer, Integer, Integer> fun) {
        return fun.apply(source.getX(), dest.getX());
    }

    /**
     * Return BiFunction for position figure by Y Cell.
     * @param source start position
     * @param dest end position
     * @param fun BiFunction
     * @return Return BiFunction for position figure by Y Cell.
     */
    int getPositionY(Cell source, Cell dest, BiFunction<Integer, Integer, Integer> fun) {
        return fun.apply(source.getY(), dest.getY());
    }
}
