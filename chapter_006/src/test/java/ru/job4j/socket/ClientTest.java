package ru.job4j.socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ClientTest {

    private Socket clientSocket;
    private Client client;

    private ByteArrayOutputStream out;
    private ByteArrayInputStream systemIn;
    private ByteArrayInputStream socketIn;

    private final InputStream stdIn = System.in;
    
    @Before
    public void setSocketAndClient() {
        this.clientSocket = mock(Socket.class);
        this.client = new Client(this.clientSocket);
    }

    @Before
    public void setOutAndIn() {
        this.out = new ByteArrayOutputStream();
        this.socketIn = new ByteArrayInputStream("dsds".getBytes());
    }

    @After
    public void backInput() {
        System.setIn(this.stdIn);
    }

    private void test(String systemIn, String socketIn, String expected) throws IOException {
        this.systemIn = new ByteArrayInputStream(systemIn.getBytes());
        this.socketIn = new ByteArrayInputStream(socketIn.getBytes());
        System.setIn(this.systemIn);
        Mockito.when(this.clientSocket.getOutputStream()).thenReturn(this.out);
        Mockito.when(this.clientSocket.getInputStream()).thenReturn(this.socketIn);
        this.client.start();
        assertThat(this.out.toString(), is(expected));
    }

    @Test
    public void whenClientSayByeThenSocketOutContainsByeAndAppStop() throws IOException {
        StringJoiner in = new StringJoiner(System.lineSeparator())
                .add("поКа");
        StringJoiner socketIn = new StringJoiner(System.lineSeparator())
                .add("Пока-пока!").add("").add("");
        StringJoiner expected = new StringJoiner(System.lineSeparator())
                .add("поКа").add("");
        test(in.toString(), socketIn.toString(), expected.toString());
    }

    @Test
    public void whenClientSayHelloAndByeThenSocketOutContainsHelloAndBye() throws IOException {
        StringJoiner in = new StringJoiner(System.lineSeparator())
                .add("Привет").add("поКа");
        StringJoiner socketIn = new StringJoiner(System.lineSeparator())
                .add("Привет, клиент!").add("").add("Пока-пока!").add("").add("");
        StringJoiner expected = new StringJoiner(System.lineSeparator())
                .add("Привет").add("поКа").add("");
        test(in.toString(), socketIn.toString(), expected.toString());
    }
}
