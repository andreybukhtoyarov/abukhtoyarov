package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is mail distribution service.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class EmailNotification {

    /**
     * Threads pool.
     */
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * takes user data and inserts it into a template:
     * subject = Notification {username} to email {email}
     * body = Add a new event to {username}.
     * @param user User.
     */
    private void emailTo(User user) {
        pool.submit(() -> send(
                String.format("Notification %s to email %s.", user.getUsername(), user.getEmail()),
                String.format("Add a new event to %s", user.getUsername()),
                user.getEmail()
        ));
    }

    /**
     * Send e:mail.
     * @param subject subject.
     * @param body body.
     * @param email email.
     */
    public void send(String subject, String body, String email) {

    }

    /**
     * Shutdown pool.
     * Wait a while for existing tasks to terminate
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
