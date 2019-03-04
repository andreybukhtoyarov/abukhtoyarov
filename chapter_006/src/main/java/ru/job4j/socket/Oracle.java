package ru.job4j.socket;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Oracle.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Oracle {
    /**
     * Oracle actions.
     */
    private final Map<String, OracleAction> actions;
    /**
     * Oracle default action.
     */
    private final OracleAction defaultAct;

    public Oracle(Map<String, OracleAction> actions, OracleAction defaultAct) {
        this.actions = actions;
        this.defaultAct = defaultAct;
    }

    /**
     * Oracle logic.
     * @param input input stream.
     * @param out output stream.
     * @param print Print Writer.
     * @throws IOException IOException.
     */
    protected void say(InputStream input, OutputStream out, PrintWriter print) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String ask;
        do {
            print.println("Wait message from users.");
            ask = reader.readLine();
            print.println(String.format("Message received: %s.", ask));
            actions.getOrDefault(ask.toLowerCase(), defaultAct).execute(ask, out);
            print.println("Response sent.");
        } while (!"пока".equalsIgnoreCase(ask));
    }
}

/**
 * Default action.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class DefaultAction implements OracleAction {

    private final List<OracleAction> actions;

    DefaultAction(List<OracleAction> actions) {
        this.actions = actions;
    }

    /**
     * Displays everything that can Oracle.
     * @param ask string ask from client.
     * @param out OutputStream.
     */
    @Override
    public void execute(String ask, OutputStream out) {
        say(out, "Я ВЕЛИКИЙ Оракл!");
        say(out, "Вот что я могу:");
        this.actions.forEach(action -> say(out, action.toString()));
        say(out, "");
    }
}

/**
 * Joke.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class Joke implements OracleAction {

    /**
     * Tells a joke.
     * @param ask string ask from client.
     * @param out OutputStream.
     */
    @Override
    public void execute(String ask, OutputStream out) {
        say(out, "Колобок повесился.");
        say(out, "");
    }

    @Override
    public String toString() {
        return "Рассказать анекдот. Для этого введите \"расскажи анекдот\".";
    }
}

/**
 * Time.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class Time implements OracleAction {

    /**
     * Get server time.
     * @param ask string ask from client.
     * @param out OutputStream.
     */
    @Override
    public void execute(String ask, OutputStream out) {
        say(out, LocalDateTime.now().toString());
        say(out, "");
    }

    @Override
    public String toString() {
        return "Сказать время. Для этого введите \"который час?\".";
    }
}

/**
 * Stop.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class Stop implements OracleAction {

    /**
     * Say bye-bye.
     * @param ask string ask from client.
     * @param out OutputStream.
     */
    @Override
    public void execute(String ask, OutputStream out) {
        say(out, "Пока-пока!");
        say(out, "");
    }

    @Override
    public String toString() {
        return "Закончить свою работу. Для этого введите \"пока\".";
    }
}
