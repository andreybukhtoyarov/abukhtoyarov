package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {
    private SimpleQueue<Integer> queue;

    @Before
    public void setQueue() {
        queue = new SimpleQueue<>();
        queue.push(0);
        queue.push(1);
        queue.push(2);
    }

    @Test
    public void whenPushNewElementThenElementPushed() {
        queue.push(3);
        assertThat(queue.size(), is(4));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenPollThenFirstElementReturnAndDeleted() {
        assertThat(queue.poll(), is(0));
        assertThat(queue.size(), is(2));
        assertThat(queue.poll(), is(1));
        assertThat(queue.size(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.size(), is(0));
        queue.poll();
    }
}