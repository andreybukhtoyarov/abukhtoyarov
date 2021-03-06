package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is simple Blocking Queue.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
@ThreadSafe
public class SimpleBlockingQueue<E> {
    /**
     * Field with data queue.
     */
    @GuardedBy("this")
    final private Queue<E> data;
    /**
     * Can the consumer take from data?
     */
    @GuardedBy("this")
    private boolean cantTake = true;

    public SimpleBlockingQueue() {
        this.data = new LinkedList<>();
    }

    /**
     * Return true if data is empty.
     * @return rue if data is empty.
     */
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    /**
     * Put element to top of queue.
     * @param element added element.
     */
    public synchronized void offer(E element) {
        while (!cantTake) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (data.offer(element)) {
            cantTake = false;
        }
        notifyAll();
    }

    /**
     * Get and remove element from top of queue.
     * @return top element from queue.
     */
    public synchronized E poll() throws InterruptedException {
        while (cantTake) {
                wait();
        }
        E element = data.poll();
        cantTake = data.isEmpty();
        notifyAll();
        return element;
    }
}
