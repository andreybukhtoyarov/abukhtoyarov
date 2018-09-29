package ru.job4j.lambda;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FunctionRangeTest {

    FunctionRange fr = new FunctionRange();

    @Test
    public void whenNumLengthIsTwo() {
        List<Double> result = fr.funcRange(1, 3, 2d, -4d);
        assertThat(result, is(Arrays.asList(-2d, 0d, 2d)));
    }

    @Test
    public void whenNumLengthIsThree() {
        List<Double> result = fr.funcRange(1, 5, 2d, -3d, 1d);
        assertThat(result, is(Arrays.asList(0d, 3d, 10d, 21d, 36d)));
    }

    @Test
    public void whenNumLengthIsOne() {
        List<Double> result = fr.funcRange(1, 2, 4d);
        assertThat(result, is(Arrays.asList(0d, 0.5d)));
    }

    @Test
    public void whenLogParamIs001ThenArithmeticException() {
        final PrintStream stdOut = System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        fr.funcRange(0,0, 1d);
        System.setOut(stdOut);
        assertThat(out.toString(), is("y = loga х (где а > 0, а ≠ 1, x > 0)\n"));
    }

}
