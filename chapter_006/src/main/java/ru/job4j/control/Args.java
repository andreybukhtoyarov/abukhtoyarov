package ru.job4j.control;

import java.util.HashMap;
import java.util.Map;

/**
 * ARGS.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Args {
    /**
     * Array with input args.
     */
    private final String[] args;
    /**
     * Actions.
     */
    private Map<String, ArgsAction> actions;

    public Args(String[] args) {
        this.args = args;
        fillActions();
    }

    /**
     * Fill actions Map.
     */
    private void fillActions() {
        actions = new HashMap<>();
        actions.put("getStartDir", new GetStartDirectory());
        actions.put("getFileName", new GetFileName());
        actions.put("getBy", new GetBy());
        actions.put("getLogPath", new GetLogPath());
        actions.put("help", new Help());
    }

    /**
     * Get arg by command.
     * @param command command
     * @return arg
     */
    public String getArg(String command) {
        ArgsAction action = this.actions.get(command);
        return action != null ? action.execute(this.args) : "";
    }
}

/**
 * Get start directory arg.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class GetStartDirectory implements ArgsAction {

    @Override
    public String execute(String[] args) {
        return findArg(args, "-d");
    }
}

/**
 * Get file name arg.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class GetFileName implements ArgsAction {

    @Override
    public String execute(String[] args) {
        return findArg(args, "-n");
    }
}

/**
 * Get log path arg.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class GetLogPath implements ArgsAction {

    @Override
    public String execute(String[] args) {
        return findArg(args, "-o");
    }
}

/**
 * Get getBy arg.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class GetBy implements ArgsAction {

    @Override
    public String execute(String[] args) {
        return findKey(args, "-m", "-f");
    }
}

/**
 * Get help arg.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
class Help implements ArgsAction {

    @Override
    public String execute(String[] args) {
        return findKey(args, "-help");
    }
}
