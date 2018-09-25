package ru.job4j.control;

public interface Input {

    String ask(String message);

    int ask(String message, int[] range);
}
