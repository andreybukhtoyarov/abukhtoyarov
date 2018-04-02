package ru.job4j.generic;

import java.util.Objects;

/**
 * This class is base for class User and Role.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public abstract class Base {
    /**
     * Id of object.
     */
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Return id.
     * @return return id.
     */
    public String getId() {
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
        return Objects.equals(id, base.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
