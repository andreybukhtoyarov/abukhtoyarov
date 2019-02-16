package ru.job4j.chat;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Log.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Log {

    private final File log;

    public Log(File log) {
        this.log = log;
    }

    protected void commandLog(String command) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
            bw.write(String.format("%s [COMMAND]: %s\n", LocalDateTime.now(), command));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void answerLog(String answer) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
            bw.write(String.format("%s [ANSWER]: %s\n", LocalDateTime.now(), answer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void askLog(String ask) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
            bw.write(String.format("%s [ASK]: %s\n", LocalDateTime.now(), ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
