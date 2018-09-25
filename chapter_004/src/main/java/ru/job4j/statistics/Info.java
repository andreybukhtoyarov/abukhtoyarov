package ru.job4j.statistics;

/**
 * Information about changes in the collection.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Info {
    /**
     * Added new users, changed users, removed users.
     */
    private int newUsers, changedUsers, deletedUsers;

    private Info(Builder builder) {
        this.newUsers = builder.newUsers;
        this.changedUsers = builder.changedUsers;
        this.deletedUsers = builder.deletedUsers;
    }

    public int getNewUsers() {
        return newUsers;
    }

    public int getChangedUsers() {
        return changedUsers;
    }

    public int getDeletedUsers() {
        return deletedUsers;
    }

    /**
     * Builder for Info.
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    public static class Builder {
        private int newUsers, changedUsers, deletedUsers = 0;

        public Builder() {
        }

        public Builder setNewUsers(int newUsers) {
            this.newUsers = newUsers;
            return this;
        }

        public Builder setChangedUsers(int changedUsers) {
            this.changedUsers = changedUsers;
            return this;
        }

        public Builder setDeletedUsers(int deletedUsers) {
            this.deletedUsers = deletedUsers;
            return this;
        }

        public Info build() {
            return new Info(this);
        }
    }
}
