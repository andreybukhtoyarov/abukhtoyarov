package ru.job4j.trackerrefactor;
/**
 * Interface for user actions.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface UserAction {
    /**
     * This method return action key.
     * @return action key.
     */
    int key();

    /**
     * This method do something.
     * @param input - input.
     * @param tracker - tracker.
     */
    void execute(Input input, ITracker tracker);

    /**
     * This method return what this method does.
     * @return - what this method does.
     */
    String info();
}
