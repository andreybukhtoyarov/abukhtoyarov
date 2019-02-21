package ru.job4j.chat;

/**
 * Interface for Console chat action.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface ConsoleChatAction {

    void execute(Log log, String ask);
}
