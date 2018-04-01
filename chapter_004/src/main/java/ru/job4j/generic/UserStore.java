package ru.job4j.generic;

public class UserStore extends AbstractStore<User> {
    private SimpleArray<User> sArray = new SimpleArray<>(5);
}
