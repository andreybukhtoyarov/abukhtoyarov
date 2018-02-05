package ru.job4j.chessboard;

/**
 * Figure Not Found Exception.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class FigureNotFoundException extends Exception {
    /**
     * Figure Not Found Exception.
     * @param message exception message.
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}
