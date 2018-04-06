package ru.job4j.list;


import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleStackTest {
    private SimpleStack<Integer> stack;

    @Before
    public void setStack() {
        stack = new SimpleStack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);
    }

    @Test
    public void whenPushNewElementThenElementPushed() {
        stack.push(3);
        assertThat(stack.size(), is(4));
    }
    
    @Test (expected = NoSuchElementException.class)
    public void whenPollThenLastElementReturnAndDeleted() {
        assertThat(stack.poll(), is(2));
        assertThat(stack.size(), is(2));
        assertThat(stack.poll(), is(1));
        assertThat(stack.size(), is(1));
        assertThat(stack.poll(), is(0));
        assertThat(stack.size(), is(0));
        stack.poll();
    }
}