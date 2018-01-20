package ru.job4j.shapes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class PaintTest {
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOut() {
        System.setOut(stdOut);
    }


    @Test
    public void whenDrawSquare() {
        new Paint(new Square()).draw();
        assertThat(
                out.toString(),
                is(new StringJoiner(System.lineSeparator())
                        .add("*****")
                        .add("*****")
                        .add("*****")
                        .add("*****")
                        .add("*****")
                        .toString())
        );
    }

    @Test
    public void whenDrawTriangle() {
        new Paint(new Triangle()).draw();
        assertThat(
                out.toString(),
                is(new StringJoiner(System.lineSeparator())
                        .add("*")
                        .add("**")
                        .add("***")
                        .add("****")
                        .add("*****")
                        .toString())
        );
    }
}
