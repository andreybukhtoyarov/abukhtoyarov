package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Class User..
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class User {
    /**
     * Name of User.
     */
    private String name;
    /**
     * Number of children.
     */
    private int children;
    /**
     * Date of birthday.
     */
    private Calendar birthday;

    /**
     * Constructor.
     * @param name name of User.
     * @param children number of children.
     * @param birthday date of birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
