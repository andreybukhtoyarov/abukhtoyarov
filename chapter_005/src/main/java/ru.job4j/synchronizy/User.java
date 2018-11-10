package ru.job4j.synchronizy;

/**
 * This class subscribe User.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class User {
    /**
     * Id of User.
     */
    private final int id;
    /**
     * Amount.
     */
    private int amount;

    public User(final int id, final int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Return this id.
     * @return this id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Return this amount.
     * @return this amount.
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Set this amount.
     * @param amount new amount.
     */
    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.id == user.id;
    }

    @Override
    public int hashCode() {
        int result = 77;
        return 31 * result + this.id;
    }
}
