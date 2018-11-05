package ru.job4j.jmm;

/**
 * This class demonstrates the property of jmm - The Race Condition.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class RaceCondition {

    /**
     * Balance of bank account. Variable member.
     */
    private int bankAccount = 10;

    /**
     * Get balance.
     * @return current balance.
     */
    int getCurrentBalance() {
        return this.bankAccount;
    }

    /**
     * Srt balance.
     * @param value count of money.
     */
     void setMoney(int value) {
        this.bankAccount = value;
    }
}

class GetMoney implements Runnable {
    /**
     * Shared Object.
     */
    RaceCondition rc = new RaceCondition();

    /**
     * Count of money withdraw.
     */
    int count = 0;

    @Override
    public void run() {
        for (int i = 1; i < 5; ++i) {
            if (getMoney(i)) {
                count += i;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /**
     * Get money from bank account.
     * @param value withdrawal amount.
     * @return true if
     */
    private boolean getMoney(int value) {
        boolean get = false;
        int currentBalance = rc.getCurrentBalance();
        System.out.printf("Поток %s. Текущий баланс = %s\n",
                Thread.currentThread().getName(), currentBalance);
        if (currentBalance >= value) {
            rc.setMoney(currentBalance - value);
            System.out.printf("Поток %s. Снял деньги со счета, текущий баланс = %s\n",
                    Thread.currentThread().getName(), rc.getCurrentBalance());
            get = true;
        } else {
            System.out.printf("Поток %s. Недостаточно денег на счету для снятия\n",
                    Thread.currentThread().getName());
        }
        return get;
    }
}

class DemonstrationRC {

    /**
     * Поток Thread-0. Текущий баланс = 10
     * Поток Thread-1. Текущий баланс = 10
     * Поток Thread-1. Снял деньги со счета, текущий баланс = 9
     * Поток Thread-0. Снял деньги со счета, текущий баланс = 9
     * Поток Thread-1. Текущий баланс = 9
     * Поток Thread-1. Снял деньги со счета, текущий баланс = 7
     * Поток Thread-0. Текущий баланс = 9
     * Поток Thread-0. Снял деньги со счета, текущий баланс = 7
     * Поток Thread-0. Текущий баланс = 7
     * Поток Thread-0. Снял деньги со счета, текущий баланс = 4
     * Поток Thread-1. Текущий баланс = 7
     * Поток Thread-1. Снял деньги со счета, текущий баланс = 4
     * Поток Thread-0. Текущий баланс = 4
     * Поток Thread-0. Снял деньги со счета, текущий баланс = 0
     * Поток Thread-1. Текущий баланс = 0
     * Поток Thread-1. Недостаточно денег на счету для снятия
     * После работы потоков баланс счета = 0
     * Денег всего было 10 у.е. а собрали = 16
     * Process finished with exit code 0
     * @param args args.
     * @throws InterruptedException InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        GetMoney g = new GetMoney();
        Thread one = new Thread(g);
        Thread two = new Thread(g);
        one.start();
        two.start();
        one.join();
        two.join();
        System.out.printf("После работы потоков баланс счета = %s\n", g.rc.getCurrentBalance());
        System.out.printf("Денег всего было 10 у.е. а собрали = %s", g.count);
    }
}
