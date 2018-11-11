package ru.job4j.synchronizy;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ThreadSafeListTest {

    private ThreadSafeList<Integer> tsl;

    @Before
    public void setTsl() {
        this.tsl = new ThreadSafeList<>();
    }

    @Before
    public void addToTls() {
        for (int i = 1; i < 4; ++i) {
            this.tsl.add(i);
        }
    }

    @Test
    public void whenCopyThenCopy() {
        Iterator<Integer> iterator = this.tsl.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
    }

}