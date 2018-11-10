package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is Thread safe.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
@ThreadSafe
public class UserStorage {
    /**
     * User storage.
     */
    @GuardedBy("this")
    private final Set<User> userStore = new HashSet<>();

    /**
     * Return count of Users.
     * @return count of Users.
     */
    synchronized int size() {
        return this.userStore.size();
    }

    /**
     * Add new user to storage.
     * @param user new user.
     * @return true if user not exist in storage.
     */
    boolean add(User user) {
        return userStore.add(user);
    }

    /**
     * Find user by id.
     * @param id id.
     * @return User with id.
     */
    User getUserById(int id) {
        List<User> users = this.userStore.stream()
                .filter(user -> user.getId() == id)
                .collect(Collectors.toList());
        return users.isEmpty() ? null : users.get(0);
    }

    /**
     * Delete user from storage.
     * @param user user.
     * @return true if user exist in storage.
     */
    boolean delete(User user) {
        return userStore.remove(user);
    }

    /**
     * Transfer money.
     * @param fromId id from where.
     * @param toId id to where.
     * @param amount amount.
     * @return true if money transferred.
     */
    synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean transferred = false;
        User from = getUserById(fromId);
        User to = getUserById(toId);
        if (from.getAmount() >= amount) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            transferred = true;
        }
        return transferred;
    }
}
