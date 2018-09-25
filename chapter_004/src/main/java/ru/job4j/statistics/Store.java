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
        int changedUsers = 0;
        int deletedUsers = 0;
        for (User userPrev : previous) {
            int idUserPrev = userPrev.id;
            boolean detected = false;
            for (User userCurrent : current) {
                if (userCurrent.id == idUserPrev) {
                    detected = true;
                    if (!userCurrent.name.equals(userPrev.name)) {
                        ++changedUsers;
                    }
                    break;
                }
            }
            if (!detected) {
                ++deletedUsers;
            }
        }
        int newUsers = current.size() - (previous.size() - deletedUsers);
        return builder.setChangedUsers(changedUsers).setNewUsers(newUsers).setDeletedUsers(deletedUsers).build();
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
