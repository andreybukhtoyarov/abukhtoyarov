package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.theInstance;

public class SimpleArrayTest {
    private SimpleArray<String> sa;

    @Before
    public void setSa() {
        this.sa = new SimpleArray<>(10);
        this.sa.add("First line");
        this.sa.add("Second line");
        this.sa.add("Third line");
    }

    @Test
    public void whenDeleteThenSizeIsTwo() {
        this.sa.delete(1);
        assertThat(this.sa.size(), is(2));
    }

    @Test
    public void whenDeleteSecondElementThenFirstAndThird() {
        this.sa.delete(1);
        String[] result = {this.sa.get(0), this.sa.get(1), null};
        String[] expected = {"First line", "Third line", null};
        assertThat(result, is(expected));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenDeleteOutOfBoundElementThenThrowNewException() {
        this.sa.delete(3);
    }

    @Test
    public void whenSetSecondElementThenSecondElementWillChange() {
        this.sa.set(1, "Change");
        assertThat(this.sa.get(1), is("Change"));
    }

    @Test
    public void whenSetElementThenSizeNotChange() {
        this.sa.set(1, "Change");
        assertThat(this.sa.size(), is(3));
    }

    @Test
    public void whenSetSecondElementThenFirstAndThirdNotChange() {
        this.sa.set(1, "Change");
        assertThat(this.sa.get(0), is("First line"));
        assertThat(this.sa.get(1), is("Change"));
        assertThat(this.sa.get(2), is("Third line"));
    }

    @Test
    public void whenAddNewElementThenSizeIsFour() {
        this.sa.add("Fourth line");
        assertThat(this.sa.size(), is(4));
    }

    @Test
    public void whenAddElementsOverCapacityThenCapacityIsIncrease() {
        for (int iter = 0; iter < 97; ++iter) {
            this.sa.add(String.format("%s %s", String.valueOf(iter + 4), "line"));
        }
        assertThat(this.sa.get(96), is("97 line"));
        assertThat(this.sa.size(), is(100));
    }

    @Test
    public void whenGetElementThenGetElement() {
        assertThat(this.sa.get(0), is("First line"));
        assertThat(this.sa.get(1), is("Second line"));
        assertThat(this.sa.get(2), is("Third line"));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnThreeElements() {
        Iterator it = sa.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("First line"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Second line"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Third line"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Iterator it = sa.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("First line"));
        assertThat(it.next(), is("Second line"));
        assertThat(it.next(), is("Third line"));
    }
}