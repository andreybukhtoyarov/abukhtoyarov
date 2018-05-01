package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TheSimpleTreeTest {
    private TheSimpleTree<Integer> tree;
    private Iterator<Integer> it;

    @Before
    public void setTree() {
        this.tree = new TheSimpleTree<>(1);
        this.tree.add(1, 2);
        this.tree.add(1, 3);
        this.tree.add(3, 4);
        this.it = this.tree.iterator();
    }

    @Test
    public void whenFindByThreeThenTrue() {
        assertThat(tree.findBy(3).isPresent(), is(true));
    }

    @Test
    public void when6ElFindLastThen6() {
        this.tree = new TheSimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        assertThat(tree.findBy(7).isPresent(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifierThenException() {
        this.tree.add(5, 5);
        it.next();
    }
}