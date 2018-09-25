package ru.job4j.control;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private StockMarket sm;

    @Before
    public void setOut() {
        System.setOut(new PrintStream(out));
    }

    @Before
    public void setSm() {
        sm = new StockMarket();
    }

    @After
    public void backOutput() {
        System.setOut(stdOut);
    }

    @Test
    public void menuShow() {
        Input inp = new StubInput(new String[] {"1", "1", "1", "y"});
        new StartUI(inp, sm).init();
        String expected = new StringJoiner(System.lineSeparator())
                .add("0. Добавить новую заявку")
                .add("1. Показать обще число ставок")
                .add("Введите пункт меню : ")
                .add("Показать ставки")
                .add("Введите бук")
                .add("Введите действие заявки")
                .add("Нет ставок у данного эмитента.")
                .add("Выйти? y или n : ")
                .add("")
                .toString();
        assertThat(out.toString(), is(expected));
    }
}