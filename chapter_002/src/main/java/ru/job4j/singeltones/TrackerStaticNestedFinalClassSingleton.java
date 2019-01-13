package ru.job4j.singeltones;

import ru.job4j.trackerrefactor.Tracker;

/**
 * Final static class singleton pattern.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class TrackerStaticNestedFinalClassSingleton {

    private TrackerStaticNestedFinalClassSingleton() {

    }

    /**
     * Get INSTANCE.
     * @return tracker.
     */
    public static Tracker getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Holder for INSTANCE.
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    private final static class Holder {
        /**
         * INSTANCE Tracker.
         */
        private static final Tracker INSTANCE = new Tracker();
    }
}
