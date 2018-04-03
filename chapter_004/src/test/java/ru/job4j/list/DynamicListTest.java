package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DynamicListTest {

    private DynamicList<Integer> list;

    @Before
    public void setList() {
        this.list = new DynamicList<>();
        for (int iter = 0; iter < 11; ++iter) {
            this.list.add(iter);
        }
    }

    @Test
    public void whenAddNewElementThenSizeIzTwelve() {
        this.list.add(11);
        assertThat(this.list.size(), is(12));
    }

    @Test
    public void whenGetElementThenElement() {
        assertThat(this.list.get(3), is(3));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetByNoExistIndexThenException() {
        this.list.get(131);
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyThenIteratorHasNextThrowConcurrentModificationException() {
        Iterator<Integer> iter = this.list.iterator();
        this.list.add(12);
        iter.hasNext();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyThenIteratorNextThrowConcurrentModificationException() {
        Iterator<Integer> iter = this.list.iterator();
        this.list.add(12);
        iter.next();
    }
}