package ru.job4j.wait;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    private SimpleBlockingQueue<Integer> sbq;
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

    @Before
    public void setSbq() {
        this.sbq = new SimpleBlockingQueue<>();
    }

    @Before
    public void setOut() {
        System.setOut(new PrintStream(byteOut));
    }

    @After
    public void stdOut() {
        System.setOut(stdOut);
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            synchronized (sbq) {
                for (int i = 0; i < 5; ++i) {
                    System.out.printf("Consumer take - %s\n", sbq.poll());
                }
            }
        }
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            synchronized (sbq) {
                for (int i = 0; i < 5; ++i) {
                    sbq.offer(i);
                    System.out.printf("Producer offer - %s\n", i);
                }
            }
        }
    }

    @Test
    public void test() {
        Thread one = new Thread(new Producer());
        Thread two = new Thread(new Consumer());
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String expected = new StringJoiner(System.lineSeparator())
                .add("Producer offer - 0")
                .add("Consumer take - 0")
                .add("Producer offer - 1")
                .add("Consumer take - 1")
                .add("Producer offer - 2")
                .add("Consumer take - 2")
                .add("Producer offer - 3")
                .add("Consumer take - 3")
                .add("Producer offer - 4")
                .add("Consumer take - 4\n")
                .toString();
        assertThat(byteOut.toString(), is(expected));
    }
}
