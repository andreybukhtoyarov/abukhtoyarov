package ru.job4j.wait;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTestTwo {

    private final SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>();

    private final ArrayList<Integer> buffer = new ArrayList<>();

    class Consumer implements Runnable {

        @Override
        public void run() {
            while (!sbq.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    buffer.add(sbq.poll());
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }

        }
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            IntStream.range(0, 5).forEach(sbq::offer);

        }
    }

    @Test
    public void whenFetchAllThenGetIt() {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

}