package ru.job4j.trackerrefactor;

/**
 * This class is stub for user input.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StubInput implements Input {
    /** */
    private final String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String message) {
        System.out.println(message);
        return this.answers[this.position++];
    }
}
