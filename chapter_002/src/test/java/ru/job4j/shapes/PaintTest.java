package ru.job4j.shapes;

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
    @Test
    public void whenDrawSquare() {
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
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

        System.setOut(stdOut);
    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
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
        System.setOut(stdOut);
    }
}
