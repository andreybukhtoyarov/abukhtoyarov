package ru.job4j.dropabuses;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * This class is stub for InputStream.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StubInput extends InputStream {

    /**
     * List with arrays of chars from lines.
     */
    private final ArrayList<byte[]> charsList = new ArrayList<>();
    /**
     * Current array of chars for method read().
     */
    private byte[] array;
    /**
     * Position for get array from List of chars array.
     */
    private int position = 0;
    /**
     * Position in array.
     */
    private int cursor = 0;

    public StubInput(String[] lines) {
        Stream.of(lines).forEach(line -> charsList.add(line.getBytes()));
        array = charsList.get(0);
    }

    @Override
    public int read() {
        int bt = -1;
        if (cursor < array.length) {
            bt = array[cursor++];
        } else if (position < charsList.size() - 1) {
            array = charsList.get(++position);
            cursor = 0;
        }
        return bt;
    }
}
