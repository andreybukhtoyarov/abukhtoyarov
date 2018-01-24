package ru.job4j.trackerrefactor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {
    private PrintStream stdOut = System.out;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backStdOut() {
        System.setOut(stdOut);
    }

    @Test
    public void whenValidateInputNotValidDataThenMessageOfNotValid() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"notValid", "1"})
        );
        input.ask("message", new int[] {1});
        assertThat(out.toString(),
                is(String.format("%s%n%s%n%s%n", "message", "Пожалуйста вводите правильные данные.", "message"))
        );
    }

    @Test
    public void whenValidateInputValidDataThenOk() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"1"})
        );
        input.ask("message", new int[] {1});
        assertThat(out.toString(),
                is(String.format("%s%n", "message"))
        );
    }


}
