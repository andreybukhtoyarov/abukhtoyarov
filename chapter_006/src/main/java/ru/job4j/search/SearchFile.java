package ru.job4j.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * This class search file.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SearchFile {
    /**
     * Founded files.
     */
    private volatile ConcurrentLinkedQueue<File> files;
    /**
     * share with folders.
     */
    private volatile ConcurrentLinkedQueue<File> folders;

    /**
     * Find files by file extension.
     * Start thread pool.
     * Add tasks to thread pool.
     * Enjoy to tasks.
     * @param parent parent folder path.
     * @param exts file extensions.
     * @return ArrayList with files.
     */
    public List<File> files(String parent, List<String> exts) {
        File root = new File(parent);
        if (root.exists() && root.isDirectory()) {
            files = new ConcurrentLinkedQueue<>();
            folders = new ConcurrentLinkedQueue<>();
            int processors = Runtime.getRuntime().availableProcessors();
            this.folders.add(root);
            ExecutorService threadPool = Executors.newFixedThreadPool(processors);
            List<Callable<String>> tasks = new ArrayList<>();
            IntStream.range(0, processors).forEach(iteration -> tasks.add(new Search(exts)));
            try {
                threadPool.invokeAll(tasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(this.files);
    }

    /**
     * This class is for parallel file search.
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    private class Search implements Callable<String> {
        /**
         * File extensions.
         */
        private final List<String> exts;
        /**
         * Variable to turn off the thread.
         */
        private int failedTryCounter = 0;

        private Search(List<String> exts) {
            this.exts = exts;
        }

        /**
         * If failedTryCounter > 1, then thread interrupt.
         * @return "complete".
         */
        @Override
        public String call() {
            while (!Thread.currentThread().isInterrupted()) {
                File folder = folders.poll();
                if (folder != null) {
                    failedTryCounter = 0;
                    File[] listFiles = folder.listFiles();
                    if (listFiles != null) {
                        Arrays.stream(listFiles).forEach(file -> {
                            if (file.isDirectory()) {
                                folders.add(file);
                            } else {
                                exts.stream().filter(name ->
                                        file.getName().endsWith(String.format(".%s", name))).
                                        forEach(name -> files.add(file));
                            }
                        });
                    }
                } else if (failedTryCounter > 1) {
                    Thread.currentThread().interrupt();
                } else {
                    ++failedTryCounter;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            return "complete";
        }
    }
}
