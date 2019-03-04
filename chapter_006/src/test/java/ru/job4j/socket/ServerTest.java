package ru.job4j.socket;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ServerTest {

    private Socket socket;
    private Server server;

    private ByteArrayOutputStream out;
    private ByteArrayInputStream in;

    @Before
    public void setSocketAndServer() {
        this.socket = mock(Socket.class);
        this.server = new Server(socket);
    }

    @Before
    public void setInAndOut() {
        this.out = new ByteArrayOutputStream();
        this.in = new ByteArrayInputStream("".getBytes());
    }

    private void test(String input, String expected) throws IOException {
        this.in = new ByteArrayInputStream(input.getBytes());
        Mockito.when(this.socket.getInputStream()).thenReturn(this.in);
        Mockito.when(this.socket.getOutputStream()).thenReturn(this.out);
        this.server.start();
        assertThat(this.out.toString(), is(expected));
    }

    @Test
    public void whenClientSayByeThenByeBye() throws IOException {
        StringJoiner expected = new StringJoiner(System.lineSeparator())
                .add("Пока-пока!").add("").add("");
        test("ПоКа", expected.toString());
    }

    @Test
    public void whenClientSayJokeThenJoke() throws IOException {
        StringJoiner in = new StringJoiner(System.lineSeparator())
                .add("Расскажи анекдоТ").add("пока");
        StringJoiner expected = new StringJoiner(System.lineSeparator())
                .add("Колобок повесился.").add("").add("Пока-пока!").add("").add("");
        test(in.toString(), expected.toString());
    }
}
