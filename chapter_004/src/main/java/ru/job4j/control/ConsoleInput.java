package ru.job4j.control;

import java.util.Scanner;

public class ConsoleInput implements Input {

    @Override
    public String ask(String message) {
        System.out.println(message);
        return new Scanner(System.in).nextLine();
    }

    @Override
    public int ask(String message, int[] range) {
        int key = Integer.parseInt(ask(message));
        boolean exist = false;
        for (int i : range) {
            if (key == i) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Выход за рамки меню.");
        }
    }
}
