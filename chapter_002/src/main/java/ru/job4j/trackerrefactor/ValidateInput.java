package ru.job4j.trackerrefactor;

/**
 * This class is pattern Decorator.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ValidateInput implements Input {

    private final Input input;

    ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String message) {
        return this.input.ask(message);
    }

    @Override
    public int ask(String message, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(message, range);
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
