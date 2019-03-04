package ru.job4j.socket;

import java.io.*;

/**
 * Oracle action.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface OracleAction {

    void execute(String ask, OutputStream out);

    default void say(OutputStream out, String message) {
        PrintWriter writer = new PrintWriter(out, true);
        writer.println(message);
    }
}
