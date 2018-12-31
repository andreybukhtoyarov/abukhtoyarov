package ru.job4j.nonblockingalgoritm;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

/**
 * This class describe model.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
@ThreadSafe
public class Base {
    /**
     * Id of model.
     */
    private final int id;
    /**
     * Version of model.
     */
    @GuardedBy("this.lock")
    private volatile int version = 0;
    /**
     * Name of model.
     */
    private String name;
    /**
     * Lock.
     */
    private final Object lock = new Object();

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Set model version.
     * @param version version.
     */
    public void setVersion(int version) {
        synchronized (this.lock) {
            this.version = version;
        }
    }

    /**
     * Get model version.
     * @return version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Get name.
     * @return this name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get id of this model.
     * @return this id.
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id
                && version == base.version
                && name.equals(base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name);
    }
}
