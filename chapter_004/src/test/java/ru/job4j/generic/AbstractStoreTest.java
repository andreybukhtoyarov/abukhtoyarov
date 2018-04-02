package ru.job4j.generic;

import org.hamcrest.core.IsNull;
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
    public void whenAddOneHundredElementsThenSizeIsOneZeroTwo() {
        for (int iterate = 0; iterate < 100; ++iterate) {
            this.userStore.add(new User(String.format("%s", iterate)));
            this.roleStore.add(new Role(String.format("%s", iterate)));
        }
        assertThat(this.userStore.size(), is(102));
        assertThat(this.roleStore.size(), is(102));
    }

    @Test
    public void whenReplaceElementsThenElementsIsReplaced() {
        boolean result = this.roleStore.replace("001", new Role("new Role"));
        boolean result2 = this.userStore.replace("100", new User("new User"));
        assertThat(result, is(true));
        assertThat(result2, is(true));
        assertThat(this.roleStore.findById("new Role"), is(new Role("new Role")));
        assertThat(this.userStore.findById("new User"), is(new User("new User")));
    }

    @Test
    public void whenReplaceElementsByNotExistIdThenReturnFalse() {
        assertThat(this.roleStore.replace("id not exist", new Role("002")), is(false));
        assertThat(this.userStore.replace("id not exist", new User("200")), is(false));
    }

    @Test
    public void whenFindByIdThenFound() {
        assertThat(this.roleStore.findById("001"), is(new Role("001")));
        assertThat(this.userStore.findById("100"), is(new User("100")));
    }

    @Test
    public void whenFindByIdAndIdNotExistThenReturnNull() {
        assertThat(this.roleStore.findById("002"), new IsNull<>());
        assertThat(this.userStore.findById("200"), new IsNull<>());
    }

    @Test
    public void whenDeleteThenReturnTrue() {
        assertThat(this.userStore.delete("100"), is(true));
        assertThat(this.roleStore.delete("001"), is(true));
    }

    @Test
    public void whenDeleteByNotExistIdThenReturnFalse() {
        assertThat(this.userStore.delete("id not exist"), is(false));
        assertThat(this.roleStore.delete("id not exist"), is(false));
    }
}