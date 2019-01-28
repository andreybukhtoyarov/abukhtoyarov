package ru.job4j.control;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is Switch for Threads.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Switcher {
    /**
     * Field with lock.
     */
    private final ReentrantLock lock = new ReentrantLock();
    /**
     * Field with condition.
     */
    private final Condition condition = lock.newCondition();
    /**
     * Field with String Holder.
     */
    private final StringHolder sh;

    public Switcher(StringHolder sh) {
        this.sh = sh;
    }

    /**
     * Append to String ten numbers. Next wakes all threads.
     * @param number number.
     */
    protected void execute(int number) {
        boolean notLock = true;
        while (notLock) {
            if (lock.tryLock()) {
                System.out.printf("%s - get lock\n", Thread.currentThread().getName());
                int count = 0;
                while (count < 10) {
                    sh.append(number);
                    ++count;
                }
                System.out.printf("%s\n", sh.get());
                notLock = false;
                condition.signalAll();
                System.out.printf("%s - signalAll and unlock\n", Thread.currentThread().getName());
                try {
                    System.out.printf("%s - await\n", Thread.currentThread().getName());
                    condition.await();
                    System.out.printf("%s - awake!\n", Thread.currentThread().getName());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

/**
 * This class is Switch for Threads.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class AddOn implements Callable<String> {
    /**
     * Field with Switcher.
     */
    private final Switcher sw;
    /**
     * Number to add.
     */
    private final int number;
    /**
     * Count of repeat.
     */
    private final int repeat;

    public AddOn(Switcher sw, int number, int repeat) {
        this.sw = sw;
        this.number = number;
        this.repeat = repeat;
    }

    @Override
    public String call() {
        int count = 0;
        while (count < repeat) {
            sw.execute(number);
            ++count;
        }
        return String.format("%s - well done!", Thread.currentThread().getName());
    }
}
