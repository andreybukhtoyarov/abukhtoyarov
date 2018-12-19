package ru.job4j.bomberman;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * This class describe cell in game board.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
@ThreadSafe
public class ReentrantLock {
    /**
     * The field shows whether it is possible to go on it.
     */
    @GuardedBy("this")
    private volatile boolean isLocked;

    private ReentrantLock(Builder builder) {
        this.isLocked = builder.isLocked;
    }

    /**
     * Set field isLocked.
     * @param locked locked.
     */
    public synchronized void setLocked(boolean locked) {
        isLocked = locked;
    }

    /**
     * Get field isLocked.
     * @return isLocked.
     */
    public synchronized boolean isLocked() {
        return isLocked;
    }

    /**
     * Builder for ReentrantLock.
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    public static class Builder {

        private boolean isLocked = false;

        public Builder() {

        }

        public ReentrantLock build() {
            return new ReentrantLock(this);
        }

        public Builder setLocked(boolean locked) {
            this.isLocked = locked;
            return this;
        }
    }
}
