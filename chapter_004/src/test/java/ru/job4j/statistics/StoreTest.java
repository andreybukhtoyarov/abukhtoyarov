package ru.job4j.statistics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class StoreTest {
    private Store st;
    private ArrayList<Store.User> prev;
    private ArrayList<Store.User> current;

    @Before
    public void setSt() {
        st = new Store();
    }

    @Before
    public void setLists() {
        prev = new ArrayList<>(
                Arrays.asList(
                        new Store.User(1, "Andrew"),
                        new Store.User(2, "Mike"),
                        new Store.User(3, "Aaron")
                ));
        current = new ArrayList<>(
                Arrays.asList(
                        new Store.User(1, "Andrew"),
                        new Store.User(2, "Mike"),
                        new Store.User(3, "Aaron")
                ));
    }

    @Test
    public void whenNoChangesThenZero() {
        Info info = st.diff(prev, current);
        assertThat(info.getChangedUsers(), is(0));
        assertThat(info.getDeletedUsers(), is(0));
        assertThat(info.getNewUsers(), is(0));
    }

    @Test
    public void whenOneUserDelThenInfoGetDeletedUsersIsOne() {
        current.remove(1);
        Info info = st.diff(prev, current);
        assertThat(info.getChangedUsers(), is(0));
        assertThat(info.getDeletedUsers(), is(1));
        assertThat(info.getNewUsers(), is(0));
    }

    @Test
    public void whenOneUserChangeThenInfoGetChangedUsersIsOne() {
        current.set(1, new Store.User(2, "lol"));
        Info info = st.diff(prev, current);
        assertThat(info.getChangedUsers(), is(1));
        assertThat(info.getDeletedUsers(), is(0));
        assertThat(info.getNewUsers(), is(0));
    }

    @Test
    public void whenAddNewUserThenInfoGetNewUsersIsOne() {
        current.add(new Store.User(4, "Andrew"));
        Info info = st.diff(prev, current);
        assertThat(info.getChangedUsers(), is(0));
        assertThat(info.getDeletedUsers(), is(0));
        assertThat(info.getNewUsers(), is(1));
    }

    @Test
    public void whenAllChangeByOneThenAllInfoGetOne() {
        current.remove(2);
        current.set(0, new Store.User(1, "lol"));
        current.add(new Store.User(4, "Andrew"));
        Info info = st.diff(prev, current);
        assertThat(info.getChangedUsers(), is(1));
        assertThat(info.getDeletedUsers(), is(1));
        assertThat(info.getNewUsers(), is(1));
    }
}