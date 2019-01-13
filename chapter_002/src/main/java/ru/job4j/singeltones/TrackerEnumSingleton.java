package ru.job4j.singeltones;

import ru.job4j.trackerrefactor.Tracker;

/**
 * Singleton pattern ENUM.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public enum TrackerEnumSingleton {

    INSTANCE();
    /**
     * Tracker.
     */
    private final Tracker singleTracker;

    TrackerEnumSingleton() {
        this.singleTracker = new Tracker();
    }

    /**
     * Get tracker.
     * @return tracker.
     */
    public Tracker getTracker() {
        return singleTracker;
    }
}
