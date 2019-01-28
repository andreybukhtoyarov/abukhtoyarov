package ru.job4j.singeltones;

import ru.job4j.trackerrefactor.Tracker;

/**
 * Static field singleton pattern.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class TrackerStaticFieldSingleton {
    /**
     * INSTANCE Tracker.
     */
    private static Tracker instance;

    private TrackerStaticFieldSingleton() {

    }

    /**
     * Get INSTANCE.
     * @return tracker.
     */
    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }
}
