package ru.job4j.control;

/**
 * User action.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public abstract class UserAction {
    /**
     * Key of action.
     */
    private final int key;
    /**
     * Info of this action.
     */
    private final String info;

    public UserAction(int key, String info) {
        this.key = key;
        this.info = info;
    }

    /**
     * Execute this action.
     * @param input input for this action.
     * @param sm Stock Market.
     */
    public abstract void execute(Input input, StockMarket sm);

    /**
     * Return key of this action.
     * @return key of this action.
     */
    public int key() {
        return this.key;
    }

    /**
     * Return info of this action.
     * @return info of this action.
     */
    public String info() {
        return String.format("%s. %s", this.key(), this.info);
    }
}
