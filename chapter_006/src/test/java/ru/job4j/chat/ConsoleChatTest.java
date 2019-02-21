package ru.job4j.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.dropabuses.StubInput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConsoleChatTest {

    private File answers;

    private File log;

    private ByteArrayOutputStream out;
    private InputStream input;
    private final String[] answersArray = {"Ответ 1\n", "Ответ 2\n", "Ответ 3\n", "Ответ 4\n"};
    private final String[] questionsArray =
            {"Привет\n", "стоп\n", "Как дела?\n", "продолжить\n", "Как погода?\n", "закончить\n"};

    private ConsoleChat chat;

    @Before
    public void setAnswer() {
        if (answers == null) {
            answers = new File(String.format("%s/chatAnswers.txt", System.getProperty("java.io.tmpdir")));
            answers.deleteOnExit();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(answers))) {
                Stream.of(answersArray).forEach(answer -> {
                    try {
                        bw.write(answer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Before
    public void setLog() throws IOException {
        log = new File(String.format("%s/chatLog.txt", System.getProperty("java.io.tmpdir")));
        log.createNewFile();
    }

    @Before
    public void setOut() {
        out = new ByteArrayOutputStream();
    }

    @Before
    public void setInput() {
        input = new StubInput(questionsArray);
    }

    @After
    public void delLog() {
        this.log.delete();
    }

    /**
     * Find match in arrays and returns they in list.
     * @param lines first array.
     * @param expected second array.
     * @return list of match.
     */
    private List<String> getListOfMatch(String[] lines, String[] expected) {
        return Stream.of(lines)
                .filter(str -> Stream.of(expected).anyMatch(exp -> str.contains(exp.subSequence(0, exp.length() - 2))))
                .collect(Collectors.toList());
    }

    @Test
    public void whenChatQuestionsArrayThenGetListOfMatchSizeIsTwo() {
        chat = new ConsoleChat(answers, new Log(log), input, out);
        chat.chat();
        List<String> resultAnswers = getListOfMatch(out.toString().split("\n"), answersArray);
        assertThat(resultAnswers.size(), is(2));
    }

    @Test
    public void whenChatQuestionsArrayThenSixMatch() throws IOException {
        chat = new ConsoleChat(answers, new Log(log), input, out);
        chat.chat();
        List<String> logLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(log))) {
            br.lines().forEach(logLines::add);
        }
        List<String> result = getListOfMatch(logLines.toArray(new String[0]), questionsArray);
        assertThat(result.size(), is(6));
    }

    @Test
    public void whenAnswersFileNotExistThen() {
        chat = new ConsoleChat(new File(""), new Log(log), input, out);
        chat.chat();
        StringBuilder sb = new StringBuilder().append("Привет\n")
                .append("Файл с ответами не найден, пуст или не является текстовым файлом.\n")
                .append("стоп\n")
                .append("Как дела?\n")
                .append("продолжить\n")
                .append("Как погода?\n")
                .append("Файл с ответами не найден, пуст или не является текстовым файлом.\n")
                .append("закончить\n");
        assertThat(this.out.toString(), is(sb.toString()));
    }

}