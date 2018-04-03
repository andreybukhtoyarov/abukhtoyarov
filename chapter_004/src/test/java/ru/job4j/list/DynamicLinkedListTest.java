package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

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
}