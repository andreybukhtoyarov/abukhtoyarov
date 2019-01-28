package ru.job4j.control;

/**
 * This class stores the string and methods for adding and taking a string.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StringHolder {
    /**
     * String Builder.
     */
    private final StringBuilder string = new StringBuilder();

    /**
     * Append number to String Builder.
     * @param number number
     * @return this.
     */
    public final StringHolder append(int number) {
        string.append(number);
        return this;
    }

    /**
     * Get String form String Builder.
     * @return string.
     */
    public final String get() {
        return this.string.toString();
    }
}
