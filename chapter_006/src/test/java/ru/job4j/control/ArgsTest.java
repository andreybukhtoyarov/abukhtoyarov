package ru.job4j.control;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArgsTest {

    private Args args;

    @Before
    public void setArgs() {
        this.args = new Args(new String[]{"-d", "/home", "-n", "name", "-o", "/log", "-m", "-f", "-help"});
    }

    @Test
    public void whenGetStartDirThen() {
        assertThat(
                args.getArg("getStartDir"),
                is("/home")
        );
    }

    @Test
    public void whenGetFileNameThen() {
        assertThat(
                args.getArg("getFileName"),
                is("name")
        );
    }

    @Test
    public void whenGetByThen() {
        assertThat(
                args.getArg("getBy"),
                is("-m")
        );
    }

    @Test
    public void whenGetLogPathThen() {
        assertThat(
                args.getArg("getLogPath"),
                is("/log")
        );
    }

    @Test
    public void whenHelpThen() {
        assertThat(
                args.getArg("help"),
                is("-help")
        );
    }

    @Test
    public void whenArgsIsEmptyThenEmptyString() {
        this.args = new Args(new String[]{});
        assertThat(
                args.getArg("help"),
                is("")
        );
    }

}