package ru.job4j.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Simple console chat.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ConsoleChat {

    private final File answers;

    private final Log log;

    private final InputStream input;

    private final OutputStream output;

    public ConsoleChat(File answers, Log log, InputStream input, OutputStream output) {
        this.answers = answers;
        this.log = log;
        this.input = input;
        this.output = output;
    }

    private List<String> getAnswers() {
        List<String> answers = new ArrayList<>();
        if (this.answers.exists() && this.answers.isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(this.answers))) {
                answers = br.lines().collect(Collectors.toList());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return answers;
    }

    private String ask() {
        String ask = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output))
        ) {
            ask = br.readLine();
            bw.write(String.format("%s\n", ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ask;
    }

    private String answer(List<String> answers) {
        String answer = answers.get(new Random().nextInt(answers.size()));
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output))) {
            bw.write(String.format("%s\n", answer));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public void chat() {
        List<String> answers = getAnswers();
        if (answers.size() != 0) {
            String ask;
            boolean respond = true;
            boolean run = true;
            while (run) {
                ask = ask();
                if ("закончить".equals(ask)) {
                    log.commandLog(ask);
                    run = false;
                    respond = false;
                } else if ("стоп".equals(ask)) {
                    log.commandLog(ask);
                    respond = false;
                } else if ("продолжить".equals(ask)) {
                    log.commandLog(ask);
                    respond = true;
                } else if (respond) {
                    log.askLog(ask);
                    log.answerLog(answer(answers));
                } else {
                    log.askLog(ask);
                }
            }
        }
    }
}
