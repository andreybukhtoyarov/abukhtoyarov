package ru.job4j.loop;

/**
 * This class contains method paint.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @since 28.12.2017.
 * @version 1.0.
 */
public class Board {
    /**
     * This method paint "chess" board size width x height.
     * @param width - width of board.
     * @param height - height of board.
     * @return "chess" board.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String lineSeparator = System.lineSeparator();

        for (int i = 1; i <= height; ++i) {
            for (int y = 1; y <= width; ++y) {
                if (i % 2 > 0 && y % 2 > 0 || i % 2 == 0 && y % 2 == 0) {
                        screen.append("x");
                    } else {
                        screen.append(" ");
                    }
            }
            screen.append(lineSeparator);
        }
        return screen.toString();
    }
}
