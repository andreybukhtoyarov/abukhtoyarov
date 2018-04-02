package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {
    private UserStore userStore;
    private RoleStore roleStore;

    @Before
    public void setUserStore() {
        this.userStore = new UserStore();
        this.userStore.add(new User("000"));
        this.userStore.add(new User("100"));
    }

    @Before
    public void setRoleStore() {
        this.roleStore = new RoleStore();
        this.roleStore.add(new Role("000"));
        this.roleStore.add(new Role("001"));
    }

    @Test
    public void whenStoreAddNewElementThenSizeIncrease() {
        this.userStore.add(new User("200"));
        this.roleStore.add(new Role("002"));
        assertThat(this.userStore.size(), is(3));
        assertThat(this.roleStore.size(), is(3));
    }

    @Test
    public void whenReplaceElementsThenElementsIsReplaced() {
        boolean result = this.roleStore.replace("002", new Role("new Role"));
        boolean result2 = this.userStore.replace("200", new User("new User"));
        assertThat(result, is(true));
        assertThat(result2, is(true));
        //assertThat(this.roleStore.findById("new Role"), is(new Role("new Role")));
    }
}