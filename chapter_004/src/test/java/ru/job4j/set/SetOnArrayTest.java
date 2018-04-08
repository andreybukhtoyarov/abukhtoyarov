package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SetOnArrayTest {
    private SetOnArray<Integer> soa;

    @Before
    public void setSoa() {
        soa = new SetOnArray<>();
        soa.add(1);
        soa.add(2);
        soa.add(3);
        soa.add(4);
    }

    @Test (expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Iterator<Integer> it = soa.iterator();
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
        Iterator<Integer> it = soa.iterator();
        soa.add(3);
        assertThat(soa.size(), is(4));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        it.next();
    }

    @Test
    public void whenAddUniqueElementThenElementAdded() {
        soa.add(5);
        Iterator<Integer> it = soa.iterator();
        assertThat(soa.size(), is(5));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
    }
}