package ru.job4j.trackerrefactor;

public class ValidateInput extends ConsoleInput {
    public int ask(String message, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(message, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Пожалуйста выберите пункт из меню.");
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста вводите правильные данные.");
            }
        } while (invalid);
        return value;
    }
}
