package ru.job4j.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    /**
     * Socket.
     */
    private final Socket socket;

    public Client(final Socket socket) {
        this.socket = socket;
    }

    /**
     * Client logic.
     * @param scanner Scanner to System.in.
     * @param out BufferedWriter to socket output stream.
     * @param in BufferedReader of socket input stream.
     * @param print BufferedWriter to System.out.
     * @throws IOException IOException.
     */
    private void execute(Scanner scanner,
                         PrintWriter out,
                         BufferedReader in,
                         PrintWriter print) throws IOException {
        String request;
        do {
            print.println("Введите сообщение серверу:");
            request = scanner.nextLine();
            out.println(request);
            String answer;
            do {
                answer = in.readLine();
                print.println(answer);
            } while (!"".equals(answer));
        } while (!"пока".equalsIgnoreCase(request));
    }

    /**
     * Start client.
     */
    public void start() {
        try (
                PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))
        ) {
            PrintWriter print = new PrintWriter(System.out, true);
            Scanner scanner = new Scanner(System.in);
            execute(scanner, out, in, print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Client(new Socket("localhost", 4444)).start();
    }
}
