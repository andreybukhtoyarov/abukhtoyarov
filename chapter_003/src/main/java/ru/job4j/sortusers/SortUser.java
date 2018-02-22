package ru.job4j.sortusers;

import java.util.*;

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
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int longest = Integer.compare(o1.getName().length(), o2.getName().length());
                return longest != 0 ? longest : o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int longest = o1.getName().compareToIgnoreCase(o2.getName());
                return longest != 0 ? longest : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return list;
    }
}
