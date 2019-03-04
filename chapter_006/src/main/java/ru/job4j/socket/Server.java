package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Server Oracle.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Server {
    /**
     * Port.
     */
    private final Socket socket;
    /**
     * Oracle actions.
     */
    private Map<String, OracleAction> actions;
    /**
     * Oracle default action.
     */
    private OracleAction defaultAct;

    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Set action.
     */
    private void setActions() {
        actions = new HashMap<>();
        actions.put("расскажи анекдот", new Joke());
        actions.put("который час?", new Time());
        actions.put("пока", new Stop());
        List<OracleAction> list = new ArrayList<>();
        actions.forEach((key, value)-> list.add(value));
        defaultAct = new DefaultAction(list);
    }

    /**
     * Start program.
     */
    public void start() throws IOException {
        setActions();
        PrintWriter print = new PrintWriter(System.out, true);
        Oracle oracle = new Oracle(actions, defaultAct);
        print.println("Connection established.");
        oracle.say(socket.getInputStream(), socket.getOutputStream(), print);

    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new ServerSocket(4444).accept()) {
            new Server(socket).start();
        }
    }
}
