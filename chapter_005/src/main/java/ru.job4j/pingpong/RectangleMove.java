package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * This class describe rectangle move.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class RectangleMove implements Runnable {
    /**
     * Rectangle.
     */
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Changes the position of the square.
     */
    @Override
    public void run() {
        Random rand = new Random();
        int stepX = rand.nextInt(3) + 1;
        int stepY = rand.nextInt(3) + 1;
        while (!Thread.currentThread().isInterrupted()) {
            stepX = getStep(this.rect.getX(), stepX, rand);
            stepY = getStep(this.rect.getY(), stepY, rand);
            this.rect.setX(this.rect.getX() + stepX);
            this.rect.setY(this.rect.getY() + stepY);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * calculates the step.
     * @param coord current coordinate.
     * @param step current step.
     * @param rand random.
     * @return new step.
     */
    private int getStep(double coord, int step, Random rand) {
        int result = step;
        if (coord != 0 && coord != 290) {
            if (coord + step > 290) {
                result = (int) coord + step - 290;
            }
            if (coord + step < 0) {
                result = step - ((int) coord + step);
            }
        }
        if (coord == 290) {
            result = rand.nextInt(3) * -1;
        } else if (coord == 0) {
            result = rand.nextInt(3);
        }
        return result;
    }
}
