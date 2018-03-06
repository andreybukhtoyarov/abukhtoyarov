package ru.job4j.bank;

import java.util.Objects;

/**
 * This class is describes the user.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class User {
    /**Field with name of user.*/
    private final String name;
    /**Field with passport of user.*/
    private final String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return this.name;
    }

    public String getPassport() {
        return this.passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return passport == user.passport && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, passport);
    }
}
