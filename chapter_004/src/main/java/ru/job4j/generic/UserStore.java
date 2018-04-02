package ru.job4j.generic;

/**
 * This class is store only for User.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class UserStore extends AbstractStore<User> {
    /**
     * Store for User. Based on SimpleArray.
     */
    private final SimpleArray<User> sArray = new SimpleArray<>(5);
}
