package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DynamicLinkedListTest {

    private DynamicLinkedList<Integer> list;

    @Before
    public void setList() {
        this.list = new DynamicLinkedList<>();
        for (int index = 0; index < 3; ++index) {
            this.list.add(index);
        }
    }

    @Test
    public void whenAddThenAdd() {
        this.list.add(3);
        this.list.add(4);
        this.list.add(5);
        assertThat(this.list.getFirst(), is(0));
        assertThat(this.list.getLast(), is(5));
        assertThat(this.list.get(1), is(1));
        assertThat(this.list.get(2), is(2));
        assertThat(this.list.get(3), is(3));
        assertThat(this.list.get(4), is(4));
        assertThat(this.list.size(), is(6));

    }

    @Test
    public void whenGetElementsThenReturnElements() {
        assertThat(this.list.get(1), is(1));
        assertThat(this.list.get(2), is(2));
        assertThat(this.list.get(2), is(2));
        assertThat(this.list.get(2), is(2));
        assertThat(this.list.get(0), is(0));
    }

    @Test (expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Iterator<Integer> it = this.list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyListThenIteratorThrowNewConcurrentModificationException() {
        Iterator<Integer> it = this.list.iterator();
        this.list.add(100);
        it.hasNext();
    }
}