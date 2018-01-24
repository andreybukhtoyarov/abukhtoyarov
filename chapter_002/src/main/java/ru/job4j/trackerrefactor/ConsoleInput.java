package ru.job4j.trackerrefactor;

import java.util.Scanner;

/**
 * This class implements Input interface.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ConsoleInput implements Input {
    /**
     * Method for user input.
     * @param message - message for user.
     * @return users answer.
     */
    @Override
    public String ask(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
