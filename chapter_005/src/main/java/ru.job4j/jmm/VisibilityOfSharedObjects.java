package ru.job4j.jmm;

/**
 * This class demonstrates the property of jmm - The Visibility of Shared Objects.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class VisibilityOfSharedObjects {
    /**
     * variable member.
     */
    int x = 1;
}

class DemonstrationVisibility {
    /**
     * Shared Object.
     */
    VisibilityOfSharedObjects vso = new VisibilityOfSharedObjects();

    Runnable runOne = () -> {
        for (int i = 0; i < 6; ++i) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            vso.x = ++vso.x;
        }
    };

    Runnable runTwo = () -> {
        for (int i = 0; i < 6; ++i) {
            if (vso.x % 2 == 0) {
                System.out.printf("this number is even - %s\n", vso.x);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    };

    /**
     * this number is even - 3
     * this number is even - 4
     * this number is even - 6
     * @param args arguments.
     */
    public static void main(String[] args) {
        DemonstrationVisibility demo = new DemonstrationVisibility();
        Thread tOne = new Thread(demo.runOne);
        Thread tTwo = new Thread(demo.runTwo);
        tOne.start();
        tTwo.start();
    }
}
