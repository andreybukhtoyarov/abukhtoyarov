package ru.job4j.shapes;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SquareTest {
    @Test
    public void whenSquarePicThenSquarePic() {
        Square square = new Square();

        String expected = new StringJoiner(System.lineSeparator())
                .add("*****")
                .add("*****")
                .add("*****")
                .add("*****")
                .add("*****")
                .toString();

        assertThat(square.pic(), is(expected));
    }
}
