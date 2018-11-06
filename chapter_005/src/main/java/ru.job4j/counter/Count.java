package ru.job4j.counter;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {

    @GuardedBy("this")
    private volatile int value;

    public synchronized void increment() {
        this.value++;
    }

    public int get() {
        return this.value;
    }
}
