package ru.job4j.control;

/**
 * Interface for class ARGS.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public interface ArgsAction {
    /**
     * Get argument.
     * @param args arguments.
     * @return argument.
     */
    String execute(String[] args);

    /**
     * Return the argument following the specified argument.
     * @param args arguments.
     * @param key specified argument.
     * @return the argument following the specified argument.
     */
    default String findArg(String[] args, String key) {
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
    default String findKey(String[] args, String... keys) {
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
}
