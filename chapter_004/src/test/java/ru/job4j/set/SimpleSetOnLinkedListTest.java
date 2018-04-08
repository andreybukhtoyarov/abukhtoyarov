package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetOnLinkedListTest {

    private SimpleSetOnLinkedList<Integer> set;

    @Before
    public void setSet() {
        set = new SimpleSetOnLinkedList<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
    }

    @Test(expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Iterator<Integer> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        it.next();
    }


    @Test (expected = NoSuchElementException.class)
    public void whenAddNotUniqueElementThenElementNotAdded() {
        Iterator<Integer> it = set.iterator();
        set.add(3);
        assertThat(set.size(), is(4));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        it.next();
    }

    @Test
    public void whenAddUniqueElementThenElementAdded() {
        set.add(5);
        Iterator<Integer> it = set.iterator();
        assertThat(set.size(), is(5));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
    }
}
