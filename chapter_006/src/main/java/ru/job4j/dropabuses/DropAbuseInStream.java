package ru.job4j.dropabuses;

import java.io.*;

/**
 * This class checks stream.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class DropAbuseInStream {
    /**
     * Drop abuse from InputStream.
     * @param in InputStream.
     * @param out OutputStream.
     * @param abuse array of string to abuse.
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        String line;
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(out))
        ) {
            while ((line = bReader.readLine()) != null) {
                for (String word : abuse) {
                    line = line.replaceAll(word, "");
                }
                bWriter.write(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
