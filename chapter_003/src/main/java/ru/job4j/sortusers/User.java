package ru.job4j.sortusers;

/**
 * Class user.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class User implements Comparable<User> {
    /**Field with name of User.*/
    private String name;
    /**Field with age of User.*/
    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.age);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
