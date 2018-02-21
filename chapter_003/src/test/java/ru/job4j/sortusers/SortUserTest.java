package ru.job4j.sortusers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class SortUserTest {
    private ArrayList<User> list;

    @Before
    public void setList() {
        this.list = new ArrayList<>(Arrays.asList(
                new User("Ms. Hatson", 55),
                new User("Vatson", 34),
                new User("Sherlok", 37)
        ));
    }

    @Test
    public void whenNoSortUsersListThenSortUsersTreeSetByAge() {
        SortUser sortUser = new SortUser();
        Set<User> expected = new TreeSet<>();
        expected.add(new User("Vatson", 34));
        expected.add(new User("Sherlok", 37));
        expected.add(new User("Ms. Hatson", 55));
        assertThat(sortUser.sort(this.list), is(expected));
    }
}
