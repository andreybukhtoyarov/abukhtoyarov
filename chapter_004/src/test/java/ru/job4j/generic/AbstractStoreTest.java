package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractStoreTest {
    private UserStore userStore;
    private RoleStore roleStore;

    @Before
    public void setUserStore() {
        this.userStore = new UserStore();
    }

    @Before
    public void setRoleStore() {
        this.roleStore = new RoleStore();
    }

    @Test
    public void whenUserStoreAddNotUserThenNotAdded() {

    }
}