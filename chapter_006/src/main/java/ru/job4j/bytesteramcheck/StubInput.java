package ru.job4j.bytesteramcheck;

import java.io.InputStream;

/**
 * This class is stub for InputStream.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StubInput extends InputStream {
    /**
     * Chars for input.
     */
    private char[] input;
    /**
     * Cursor position.
     */
    private int position = 0;

    public StubInput(char[] input) {
        this.input = input;
    }

    @Override
    public int read() {
        return position == input.length ? -1 : this.input[position++];
    }
}
