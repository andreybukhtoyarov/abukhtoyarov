package ru.job4j.control;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Consumer;

/**
 * File scanner.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class FileScanner {
    /**
     * Output stream for message.
     */
    private final OutputStream outMessage;
    /**
     * Arguments.
     */
    private final String[] args;
    /**
     * Result of program.
     */
    private final List<Path> result = new ArrayList<>();
    /**
     * Start directory.
     */
    private Path startDir;
    /**
     * File name.
     */
    private String fileName;
    /**
     * Get by.
     */
    private String getBy;
    /**
     * Log path.
     */
    private Path logPath;
    /**
     * List of exceptions.
     */
    private final List<Exception> listExceptions = new ArrayList<>();

    public FileScanner(final OutputStream outMessage, final String[] args) {
        this.outMessage = outMessage;
        this.args = args;
    }

    /**
     * Map with actions.
     */
    private final Map<String, Consumer<FileScanner>> actions = new HashMap<>();

    /**
     * Fill Map with actions.
     */
    private void fillActions() {
        this.actions.put("-m", this::findByMask);
        this.actions.put("-f", this::findByMask);
        this.actions.put("-help", this::getHelp);
    }

    /**
     * Get arguments.
     */
    private void getArgs() {
        final Args arg = new Args(this.args);
        this.getBy = arg.getArg("getBy");
        if (!"-help".equals(this.getBy)) {
            this.startDir = Paths.get(arg.getArg("getStartDir"));
            this.fileName = arg.getArg("getFileName");
            this.logPath = Paths.get(arg.getArg("getLogPath"));
        }
    }

    /**
     * Mask search method.
     * @param fileScanner this.
     */
    private void findByMask(final FileScanner fileScanner) {
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(this.logPath.toFile()), true)) {
                Files.walkFileTree(fileScanner.startDir, new FSFileVisitor(fileScanner));
                this.result.forEach(writer::println);
            } catch (IOException ioe) {
                this.listExceptions.add(ioe);
            }

    }

    /**
     * Validate start directory path.
     * @throws IllegalArgumentException if path wrong.
     */
    private void validateStartDir() throws IllegalArgumentException {
        if (!Files.isDirectory(this.startDir, LinkOption.NOFOLLOW_LINKS)) {
            throw new IllegalArgumentException("wrong start path");
        }
    }

    /**
     * Validate file name.
     * @throws IllegalArgumentException if name not specified.
     */
    private void validateFileName() throws IllegalArgumentException {
        if ("".equals(this.fileName)) {
            throw new IllegalArgumentException("name not specified");
        }
    }

    /**
     * Validate get by.
     * @throws IllegalArgumentException if no search method selected.
     */
    private void validateGetBy() throws IllegalArgumentException {
        if ("".equals(this.getBy)) {
            throw new IllegalArgumentException("no search method selected");
        }
    }

    /**
     * Validate log path.
     * @throws IllegalArgumentException if wrong log path, or a file with that name already exists.
     */
    private void validateLogPath() throws IllegalArgumentException {
        if (Files.exists(this.logPath, LinkOption.NOFOLLOW_LINKS)
                || !Files.exists(this.logPath.getParent(), LinkOption.NOFOLLOW_LINKS)) {
            throw new IllegalArgumentException("wrong log path, or a file with that name already exists");
        }
    }

    /**
     * Validate all arguments.
     * @throws IllegalArgumentException if on of argument wrong.
     */
    private void validate() throws IllegalArgumentException {
        if (!"-help".equals(this.getBy)) {
            validateStartDir();
            validateFileName();
            validateGetBy();
            validateLogPath();
        }
    }

    /**
     * Print help message.
     * @param fileScanner this.
     */
    private void getHelp(final FileScanner fileScanner) {
        StringJoiner help = new StringJoiner(System.lineSeparator())
                .add("To find a file or folder, enter a command like:")
                .add("-d path to start search -n file name -m / -f type of search by mask / full name -o path to save the result")
                .add("all parts of the team separated by spaces.")
                .add("-help to get help");
        try (PrintWriter writer = new PrintWriter(fileScanner.outMessage, true)) {
            writer.println(help.toString());
        }
    }

    /**
     * Execute program.
     * @throws IllegalArgumentException if on of argument wrong.
     */
    private void execute() throws IllegalArgumentException {
        getArgs();
        fillActions();
        validate();
        this.actions.get(this.getBy).accept(this);
    }

    /**
     * Program start and exception handling.
     */
    public void start() {
        try {
            execute();
        } catch (IllegalArgumentException exc) {
            this.listExceptions.add(exc);
        }
        if (!this.listExceptions.isEmpty()) {
            try (PrintWriter writer = new PrintWriter(this.outMessage, true)) {
                this.listExceptions.forEach(exc -> writer.println(exc.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        new FileScanner(System.out, args).start();
    }

    /**
     * File visitor for FileScanner..
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    private class FSFileVisitor extends SimpleFileVisitor<Path> {
        /**
         * FileScanner.
         */
        private final FileScanner fileScanner;

        private FSFileVisitor(FileScanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            DirectoryStream<Path> dirStr = Files.newDirectoryStream(dir, fileScanner.fileName);
            dirStr.forEach(fileScanner.result::add);
            return FileVisitResult.CONTINUE;
        }
    }
}
