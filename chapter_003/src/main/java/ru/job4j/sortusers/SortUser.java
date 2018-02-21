package ru.job4j.sortusers;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class with method sort.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SortUser {
    /**
     * Sorts Users by age.
     * @param list - List with Users.
     * @return - TreeSet<> of Users.
     */
    public Set<User> sort (List<User> list) {
        return new TreeSet<>(list);
    }
}
