package ru.job4j.statistics;

import java.util.List;

/**
 * Information about changes in the collection.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Store {
    /**
     * Return information about changes in the collection.
     * @param previous previous collection.
     * @param current current collection.
     * @return information about changes in the collection.
     */
    Info diff(List<User> previous, List<User> current) {
        Info.Builder builder = new Info.Builder();
        previous.forEach(prev -> {
            current.stream().filter(cure -> prev.id == cure.id && !prev.name.equals(cure.name))
                    .forEach(c -> builder.setChangedUsers(builder.getChangedUsers() + 1));
            if (current.stream().noneMatch(cure -> prev.id == cure.id)) {
                builder.setDeletedUsers(builder.getDeletedUsers() + 1);
            }
        });
        return builder.setNewUsers(current.size() - (previous.size() - builder.getDeletedUsers())).build();
    }

    /**
     * The class describes the user.
     */
    static class User {
        /**
         * User id.
         */
        int id;
        /**
         * User name.
         */
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
