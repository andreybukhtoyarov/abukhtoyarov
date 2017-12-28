package ru.job4j.loop;

/**
 * This class contain method which calculate factorial.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @since 28.12.2017.
 * @version 1.0.
 */
public class Factorial {

    /**
     * This method calculate factorial.
     * @param n - positive integer.
     * @return factorial of number n.
     */
    public int calc(int n) {
        // Check the number n on positivity and integrity.
        // And calculate factorial.
        if (n < 0 || n % 1 > 0) {
            throw new ArithmeticException();
        } else {
            int factorial = 1;
            for (int i = n; i > 0; --i) {
                factorial = factorial * i;
            }
            return factorial;
        }
    }
}
