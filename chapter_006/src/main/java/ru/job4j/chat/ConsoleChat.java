package ru.job4j.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Simple console chat.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ConsoleChat {
    /**
     * Path to file with answers.
     */
    private final File answers;
    /**
     * Log.
     */
    private final Log log;
    /**
     * Input.
     */
    private final InputStream input;
    /**
     * Output.
     */
    private final OutputStream output;
    /**
     * If true - program is runs.
     */
    private boolean run = true;
    /**
     * If true - program is responds.
     */
    private boolean respond = true;
    /**
     * Console Chat Actions.
     */
    private final ConsoleChatAction[] actions;

    public ConsoleChat(File answers, Log log, InputStream input, OutputStream output) {
        this.answers = answers;
        this.log = log;
        this.input = input;
        this.output = output;
        this.actions = new ConsoleChatAction[]{new Finish(), new Stop(), new Continue(), new Answer()};
    }

    /**
     * Get lines with answers from file.
     * @return ArrayList with answers.
     */
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

    /**
     * Get line.
     * @return line.
     */
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

    /**
     * Get random answer from list of answers.
     * @param answers list of answers.
     * @return answer.
     */
    private String answer(List<String> answers) {
        String answer = answers.get(new Random().nextInt(answers.size()));
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output))) {
            bw.write(String.format("%s\n", answer));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    /**
     * Main program cycle.
     */
    public void chat() {
        String ask;
        while (run) {
            ask = ask();
            switch (ask) {
                case "закончить" :
                    this.actions[0].execute(this.log, ask);
                    break;
                case "стоп" :
                    this.actions[1].execute(this.log, ask);
                    break;
                case "продолжить" :
                    this.actions[2].execute(this.log, ask);
                    break;
                default :
                    this.actions[3].execute(this.log, ask);
                    break;
            }
        }
    }

    /**
     * Finish.
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    private class Finish implements ConsoleChatAction {

        @Override
        public void execute(Log log, String ask) {
            run = false;
            log.commandLog(ask);
        }
    }

    /**
     * Stop.
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    private class Stop implements ConsoleChatAction {

        @Override
        public void execute(Log log, String ask) {
            respond = false;
            log.commandLog(ask);
        }
    }

    /**
     * Continue.
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    private class Continue implements ConsoleChatAction {

        @Override
        public void execute(Log log, String ask) {
            respond = true;
            log.commandLog(ask);
        }
    }

    /**
     * Answer.
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    private class Answer implements ConsoleChatAction {

        private final List<String> answers;

        public Answer() {
            this.answers = getAnswers();
        }

        @Override
        public void execute(Log log, String ask) {
            log.askLog(ask);
            if (respond && answers.size() != 0) {
                log.answerLog(answer(answers));
            } else if (respond) {
                log.answerLog(answer(new ArrayList<>(
                        Collections.singletonList("Файл с ответами не найден, пуст или не является текстовым файлом.")
                )));
            }
        }
    }
}
