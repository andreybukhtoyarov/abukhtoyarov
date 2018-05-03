package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> tree;
    private Iterator<Integer> it;

    @Before
    public void setTree() {
        tree = new BinarySearchTree<>();
        tree.add(20);
        tree.add(18);
        tree.add(23);
        tree.add(21);
        tree.add(25);
        tree.add(17);
        tree.add(19);
        tree.add(16);
    }

    @Before
    public void setIt() {
        it = tree.iterator();
    }

    @Test
    public void whenGetSizeThenReturnThree() {
        assertThat(tree.getSize(), is(8));
    }

    @Test
    public void whenAddThenReturnTrueAndSizeIsFour() {
        assertThat(tree.add(3), is(true));
        assertThat(tree.getSize(), is(9));
    }

    @Test (expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(20));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(18));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(23));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(17));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(19));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(21));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(25));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(16));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(20));
        assertThat(it.next(), is(18));
        assertThat(it.next(), is(23));
        assertThat(it.next(), is(17));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifierThenException() {
        tree.add(5);
        it.next();
    }
}