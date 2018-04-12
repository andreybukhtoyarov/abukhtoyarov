package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {
    private User first;
    private User second;

    @Before
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        first = new User("Andrew", 2, calendar);
        second = new User("Andrew", 2, calendar);
    }

    @Test
    public void print() {
        Map<User, Object> map = new HashMap<>();
        map.put(first, 1);
        map.put(second, 1);
        System.out.println(map);
    }

}