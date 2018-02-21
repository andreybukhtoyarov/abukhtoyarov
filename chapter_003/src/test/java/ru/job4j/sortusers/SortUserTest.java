package ru.job4j.sortusers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.*;

public class SortUserTest {
    private ArrayList<User> list;

    @Before
    public void setList() {
        this.list = new ArrayList<>(Arrays.asList(
                new User("Ms. Hatson", 55),
                new User("Vatson", 34),
                new User("Sherlok", 37),
                new User("Ms. Hatson", 50),
                new User("Lestrid", 33)
        ));
    }

    @Test
    public void whenNoSortUsersListThenSortUsersTreeSetByAge() {
        SortUser sortUser = new SortUser();
        Set<User> expected = new TreeSet<>();
        expected.add(new User("Lestrid", 33));
        expected.add(new User("Vatson", 34));
        expected.add(new User("Sherlok", 37));
        expected.add(new User("Ms. Hatson", 50));
        expected.add(new User("Ms. Hatson", 55));
        assertThat(sortUser.sort(this.list), is(expected));
    }

    @Test
    public void whenNoSortByNameUsersThenSortByNameUsers() {
        SortUser sortUser = new SortUser();
        List<User> expected = new ArrayList<>(Arrays.asList(
                new User("Vatson", 34),
                new User("Lestrid", 33),
                new User("Sherlok", 37),
                new User("Ms. Hatson", 55),
                new User("Ms. Hatson", 50)
        ));
        assertThat(sortUser.sortNameLength(this.list), is(expected));
    }

    @Test
    public void whenNoSortByNameAndAgeThenSortByNameAndAge() {
        SortUser sortUser = new SortUser();
        List<User> expected = new ArrayList<>(Arrays.asList(
                new User("Lestrid", 33),
                new User("Ms. Hatson", 50),
                new User("Ms. Hatson", 55),
                new User("Sherlok", 37),
                new User("Vatson", 34)

        ));
        assertThat(sortUser.sortByAllFields(this.list), is(expected));
    }
}
