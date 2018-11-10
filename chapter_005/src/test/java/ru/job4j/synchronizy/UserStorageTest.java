package ru.job4j.synchronizy;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    class RunTransfer implements Runnable {

        @Override
        public void run() {
            us.transfer(1, 3, 200);
        }
    }

    class RunDelete implements Runnable {

        @Override
        public void run() {
            us.delete(new User(1, 0));
        }
    }

    class RunAdd implements Runnable {

        @Override
        public void run() {
            for (int i = 4; i < 7; ++i) {
                us.add(new User(i, 100 * i));
            }
        }
    }

    private UserStorage us;

    @Before
    public void setUs() {
        this.us = new UserStorage();
    }

    @Before
    public void addUsers() {
        this.us.add(new User(1, 300));
        this.us.add(new User(2, 200));
        this.us.add(new User(3, 100));
    }

    private void execute(Runnable run) {
        Thread tOne = new Thread(run);
        Thread tTwo = new Thread(run);
        Thread tThree = new Thread(run);
        tOne.start();
        tTwo.start();
        tThree.start();
        try {
            tOne.join();
            tTwo.join();
            tThree.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void whenAddNewUserThenSizeIsFour() {
        this.us.add(new User(4, 22));
        assertThat(this.us.size(), is(4));
    }

    @Test
    public void whenAddNewUserWithOldIdThenUserNotAddedAndAmountNotChanged() {
        this.us.add(new User(3, 22));
        assertThat(this.us.getUserById(3).getAmount(), is(100));
    }

    @Test
    public void delete() {
        execute(new RunDelete());
        assertThat(us.size(), is(2));
    }

    @Test
    public void transfer() {
        execute(new RunTransfer());
        assertThat(us.getUserById(1).getAmount(), is(100));
        assertThat(us.getUserById(3).getAmount(), is(300));
    }

    @Test
    public void add() {
        execute(new RunAdd());
        assertThat(us.size(), is(6));
        assertThat(us.getUserById(4).getAmount(), is(400));
        assertThat(us.getUserById(5).getAmount(), is(500));
        assertThat(us.getUserById(6).getAmount(), is(600));
    }
}
