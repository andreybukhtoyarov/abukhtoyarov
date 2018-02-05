package ru.job4j.chessboard;

/**
 * Occupied Way Exception.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class OccupiedWayException extends Exception {
    /**
     * Occupied Way Exception.
     * @param message exception message.
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
