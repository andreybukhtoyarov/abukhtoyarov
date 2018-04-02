package ru.job4j.generic;

/**
 * This class is store only for Role.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class RoleStore extends AbstractStore<Role> {
    /**
     * Store for Role. Based on SimpleArray.
     */
    private final SimpleArray<Role> sArray = new SimpleArray<>(5);
}
