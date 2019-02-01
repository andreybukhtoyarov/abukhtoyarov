package ru.job4j.dropabuses;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is stub for InputStream.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StubInput extends InputStream {
    /**
     * Array with lines.
     */
    private final String[] lines;
    /**
     * List with arrays of chars from lines.
     */
    private final ArrayList<char[]> charsList = new ArrayList<>();
    /**
     * Current array of chars for method read().
     */
    private char[] array;
    /**
     * Position for get array from List of chars array.
     */
    private int position = 0;
    /**
     * Position in array.
     */
    private int cursor = 0;

    public StubInput(String[] lines) {
        this.lines = lines;
        Arrays.stream(lines).map(line -> charsList.add(line.toCharArray())).count();
        array = charsList.get(0);
    }

    @Override
    public int read() {
        int character = -1;
        if (cursor < array.length) {
            character = array[cursor++];
        } else if (position < charsList.size() - 1) {
            array = charsList.get(++position);
            cursor = 0;
        }
        return character;
    }
}
