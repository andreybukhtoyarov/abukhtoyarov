package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SetOnHashTableTest {

    private SetOnHashTable<Integer> hashTable;

    @Before
    public void setHashTable() {
        hashTable = new SetOnHashTable<>();
        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(3);
        hashTable.add(4213);
    }

    @Test
    public void whenAddNewElementThenSizeIzFive() {
        hashTable.add(530);
        assertThat(hashTable.size(), is(5));
    }

    @Test
    public void whenAddNotUniqueElementThenNotAdded() {
        hashTable.add(4213);
        assertThat(hashTable.size(), is(4));

    }

    @Test
    public void whenRemoveThenSizeIsThree() {
        hashTable.remove(3);
        assertThat(hashTable.size(), is(3));
    }

    @Test
    public void whenRemoveThenContainsIsFalse() {
        hashTable.remove(3);
        assertThat(hashTable.contains(3), is(false));
    }

    @Test
    public void whenContainsExistElementThenTrue() {
        assertThat(hashTable.contains(2), is(true));
        assertThat(hashTable.contains(4213), is(true));
        assertThat(hashTable.contains(3), is(true));
        assertThat(hashTable.contains(1), is(true));
    }

    @Test
    public void whenContainsNotExistElementThenFalse() {
        assertThat(hashTable.contains(null), is(false));
        assertThat(hashTable.contains(333), is(false));
    }
}
