package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import  org.hamcrest.core.IsNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMapTest {
    private SimpleHashMap<Integer, String> map;

    @Before
    public void setMap() {
        map = new SimpleHashMap<>();
    }

    @Test
    public void whenAddNewElementThenReturnTrue() {
        assertThat(map.insert(1, "One"), is(true));
    }

    @Test
    public void whenAddNewElementThenSizeIzOne() {
        map.insert(1, "One");
        assertThat(map.getSize(), is(1));
    }

    @Test
    public void whenAddElementsWithEqualsKeyThenAddedFirstElement() {
        map.insert(1, "One");
        map.insert(1, "Two");
        map.insert(1, "Three");
        assertThat(map.getSize(), is(1));
    }

    @Test
    public void whenGetElementThenGetElement() {
        map.insert(1, "One");
        assertThat(map.get(1), is("One"));
    }

    @Test
    public void whenGetNotExistElementThenReturnNull() {
        map.insert(1, "One");
        assertThat(map.get(2), new IsNull<>());
    }

    @Test
    public void whenDeleteElementThenSizeIsTwo() {
        map.insert(1, "One");
        map.insert(2, "Two");
        map.insert(3, "Three");
        map.delete(2);
        assertThat(map.getSize(), is(2));
    }

    @Test
    public void whenDeleteElementThenReturnTrue() {
        map.insert(1, "One");
        map.insert(2, "Two");
        map.insert(3, "Three");
        assertThat(map.delete(3), is(true));
    }

    @Test
    public void whenDeleteNotExistElementThenReturnFalse() {
        assertThat(map.delete(3), is(false));
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        map.insert(1, "One");
        map.insert(2, "Two");
        map.insert(3, "Three");
        Iterator<SimpleHashMap.Bucket<Integer, String>> it = map.iterator();
        assertThat(it.next().getValue(), is("One"));
        assertThat(it.next().getValue(), is("Two"));
        assertThat(it.next().getValue(), is("Three"));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        map.insert(1, "One");
        map.insert(2, "Two");
        map.insert(3, "Three");
        Iterator<SimpleHashMap.Bucket<Integer, String>> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getValue(), is("One"));
        assertThat(it.next().getValue(), is("Two"));
        assertThat(it.next().getValue(), is("Three"));
    }

    @Test (expected = NoSuchElementException.class)
    public void hasNextNextSequentialInvocation() {
        map.insert(1, "One");
        map.insert(2, "Two");
        map.insert(3, "Three");
        Iterator<SimpleHashMap.Bucket<Integer, String>> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getKey(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getKey(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getKey(), is(3));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyThenException() {
        map.insert(1, "One");
        map.insert(2, "Two");
        Iterator<SimpleHashMap.Bucket<Integer, String>> it = map.iterator();
        map.delete(2);
        it.next();
    }

}