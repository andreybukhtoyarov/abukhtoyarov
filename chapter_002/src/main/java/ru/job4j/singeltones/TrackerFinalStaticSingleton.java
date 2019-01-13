package ru.job4j.singeltones;

import ru.job4j.trackerrefactor.Tracker;

/**
 * Final static field singleton pattern.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class TrackerFinalStaticSingleton {
    /**
     * INSTANCE Tracker.
     */
    private final static Tracker INSTANCE = new Tracker();

    private TrackerFinalStaticSingleton() {

    }

    /**
     * Get INSTANCE.
     * @return tracker.
     */
    public static Tracker getInstance() {
        return INSTANCE;
    }
}
