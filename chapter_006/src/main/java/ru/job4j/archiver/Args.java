package ru.job4j.archiver;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describe args.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Args {
    /**
     * Args.
     */
    private final String[] args;

    public Args(String[] args) {
        this.args = args;
    }

    /**
     * Returns the path to the archive directory.
     * @return directory patch.
     */
    String directory() {
        String dir = "";
        boolean ok = false;
        for (String str : args) {
            if (ok && !"-e".equals(str) && !"-o".equals(str)) {
                dir = str;
                break;
            }
            if ("-d".equals(str)) {
                ok = true;
            }
        }
        return dir;
    }

    /**
     * Returns a list of extensions to be excluded from the archive.
     * @return a list of extensions to be excluded from the archive.
     */
    List<String> exclude() {
        List<String> exclude = new ArrayList<>();
        boolean ok = false;
        for (String str : args) {
            if (ok && !"-o".equals(str) && !"-d".equals(str)) {
                exclude.add(str);
            } else if (ok) {
                break;
            }
            if ("-e".equals(str)) {
                ok = true;
            }
        }
        return exclude;
    }

    /**
     * Returns the path to the directory where the archive file will be saved.
     * @return the path to the directory.
     */
    String output() {
        String dirOut = "";
        boolean ok = false;
        for (String str : args) {
            if (ok && !"-e".equals(str) && !"-d".equals(str)) {
                dirOut = str;
                break;
            }
            if ("-o".equals(str)) {
                ok = true;
            }
        }
        return dirOut;
    }
}
