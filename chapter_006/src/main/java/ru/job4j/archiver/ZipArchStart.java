package ru.job4j.archiver;

/**
 * Zip archivator start.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ZipArchStart {

    public static void main(String[] args) {
        new ZipArchiver(args).archive();
    }
}
