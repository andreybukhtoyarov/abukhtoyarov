package ru.job4j.chessboard;

/**
 * Impossible Move Exception.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ImpossibleMoveException extends Exception {
    /**
     * Impossible Move Exception.
     * @param message exception message.
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
