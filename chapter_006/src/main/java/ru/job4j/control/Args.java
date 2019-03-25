package ru.job4j.control;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
    private Map<String, Function<Args, String>> actions;

    public Args(String[] args) {
        this.args = args;
        fillActions();
    }

    /**
     * Fill actions Map.
     */
    private void fillActions() {
        actions = new HashMap<>();
        actions.put("getStartDir", this::getStartDirectory);
        actions.put("getFileName", this::getFileName);
        actions.put("getBy", this::getBy);
        actions.put("getLogPath", this::getLogPath);
    }

    /**
     * Get arg by command.
     * @param command command
     * @return arg
     */
    public String getArg(String command) {
        return this.actions.get(command).apply(this);
    }

    /**
     * Return the argument following the specified argument.
     * @param args arguments.
     * @param key specified argument.
     * @return the argument following the specified argument.
     */
    private String findArg(String[] args, String key) {
        String arg = "";
        String prev = args[0];
        for (int iteration = 1; iteration < args.length; ++iteration) {
            if (key.equalsIgnoreCase(prev)) {
                arg = args[iteration];
                break;
            }
            prev = args[iteration];
        }
        return arg;
    }

    /**
     * Return the first argument that matched one of the given arguments.
     * @param args arguments.
     * @param keys specified arguments.
     * @return the first argument that matched one of the given arguments.
     */
    private String findKey(String[] args, String... keys) {
        String result = "";
        for (String arg : args) {
            for (String key : keys) {
                if (arg.equalsIgnoreCase(key)) {
                    result = key;
                    break;
                }
            }
            if (!"".equalsIgnoreCase(result)) {
                break;
            }
        }
        return result;
    }

    /**
     * Get start directory path.
     * @param args this.
     * @return start directory path.
     */
    private String getStartDirectory(Args args) {
        return findArg(args.args, "-d");
    }

    /**
     * Get file name.
     * @param args this.
     * @return file name.
     */
    private String getFileName(Args args) {
        return findArg(args.args, "-n");
    }

    /**
     * Get log path.
     * @param args this.
     * @return log path.
     */
    private String getLogPath(Args args) {
        return findArg(args.args, "-o");
    }

    /**
     * Get by.
     * @param args this.
     * @return get by.
     */
    private String getBy(Args args) {
        return findKey(args.args, "-help", "-m", "-f");
    }
}
