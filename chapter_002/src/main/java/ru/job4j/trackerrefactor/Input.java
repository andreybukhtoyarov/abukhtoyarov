package ru.job4j.trackerrefactor;

/**
 * Interface for input.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
interface Input {
    /**
     * Method for user input.
     * @param message - message for user.
     * @return users answer.
     */
    String ask(String message);

    int ask(String message, int[] range);
}
