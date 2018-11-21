package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This class is simple ThreadPool.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ThreadPool {
    /**
     * List of Threads.
     */
    private final List<Thread> threads;
    /**
     * Blocking queue of task.
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    /**
     * Number of system processors.
     */
    private final int processors = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        this.threads = new LinkedList<>();
        addAndStartThreads();
    }

    /**
     * Add threads to threads list fnd start Threads.
     */
    private void addAndStartThreads() {
        IntStream.range(0, processors).forEach((x) -> this.threads.add(createThread()));
        this.threads.forEach(Thread::start);
    }

    /**
     * Join to all Threads.
     */
    public void joinPool() {
        threads.forEach(thread -> {
            try {
                thread.join(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create Thread.
     * @return new Thread.
     */
    private Thread createThread() {
        return new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            this.tasks.poll().run();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    /**
     * Add variable of type Runnable to tasks.
     * @param job variable of type Runnable.
     */
    public void work(Runnable job) {
        this.tasks.offer(job);
    }

    /**
     * Shutdown Threads.
     */
    public void shutdown() {
        this.threads.forEach(Thread::interrupt);
        IntStream.range(0, processors).forEach((x) -> threads.add(null));
    }
}
