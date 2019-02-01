package ru.job4j.bytesteramcheck;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * This class checks parity number in stream.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class EvenChecker {
    /**
     * Check number in InputStream is even.
     * @param in InputStream.
     * @return if is even return true.
     */
    boolean isEven(InputStream in) {
        boolean isEven;
        if (in instanceof ByteArrayInputStream) {
            isEven = isEvenByte(in);
        } else {
            isEven = isEvenCharacter(in);
        }
        return isEven;
    }

    /**
     * Check number in ByteArrayInputStream is even.
     * @param in ByteArrayInputStream.
     * @return if is even return true.
     */
    boolean isEvenByte(InputStream in) {
        boolean isEven = false;
        byte[] numbers = new byte[4];
        try (BufferedInputStream bis = new BufferedInputStream(in)) {
            bis.read(numbers);
            int number = ByteBuffer.wrap(numbers).getInt();
            if (number % 2 == 0) {
                isEven = true;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return isEven;
    }

    /**
     * Check number in InputStream is even.
     * @param in InputStream.
     * @return if is even return true.
     */
    boolean isEvenCharacter(InputStream in) {
        boolean isEven = false;
        StringBuilder sBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            int number;
            do {
                number = reader.read();
                if (number != -1) {
                    sBuilder.append((char) number);
                }
            } while (number != -1);
            int numbers = Integer.valueOf(sBuilder.toString());
            if (numbers % 2 == 0) {
                isEven = true;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return isEven;
    }
}
