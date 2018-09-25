package ru.job4j.control;

import java.util.Random;

/**
 * Bid for Stock Market.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Bid {
    /**
     * Id of bid.
     */
    private int id;
    /**
     * Book of this bid.
     */
    private final int book;
    /**
     * Add bid - 1, delete bid - 0.
     */
    private final int type;
    /**
     * Bid - 1, ask - 0.
     */
    private final int action;
    /**
     * Price of this bid.
     */
    private final int price;
    /**
     * Volume of bids.
     */
    private int volume;

    private Bid(Builder builder) {
        this.id = randomID();
        this.book = builder.book;
        this.type = builder.type;
        this.action = builder.action;
        this.price = builder.price;
        this.volume = builder.volume;
    }

    /**
     * Return price of this bid.
     * @return price of this bid
     */
    public int getPrice() {
        return price;
    }

    /**
     * Return action of this bid.
     * Bid - 1, ask - 0.
     * @return action of this bid.
     */
    public int getAction() {
        return action;
    }

    /**
     * Return id of this bid.
     * @return id of this bid.
     */
    public int getId() {
        return id;
    }

    /**
     * Return book of this bid.
     * @return book of this bid.
     */
    public int getBook() {
        return book;
    }

    /**
     * Return type of this bid.
     * Add bid - 1, delete bid - 0.
     * @return type of this bid.
     */
    public int getType() {
        return type;
    }

    /**
     * Return volume of this bids.
     * @return volume of this bids.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Set up volume of this bids.
     * @param value volume of this bids.
     * @return volume.
     */
    public int setVolume(int value) {
        this.volume = value;
        return this.volume;
    }

    /**
     * Set up Id to this bid.
     * @param id Id to this bid.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method generate random ID.
     * @return random ID.
     */
    private int randomID() {
        long randNumber = new Random().nextLong() + System.currentTimeMillis();
        return (int) (randNumber ^ (randNumber >>> 32));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bid)) {
            return false;
        }
        Bid bid = (Bid) o;
        return this.volume == bid.volume && this.price == bid.price;
    }

    @Override
    public int hashCode() {
        return this.volume + this.price + 31;
    }

    /**
     * Builder for Bid.
     * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
     * @version %Id%.
     * @since 0.1.
     */
    public static class Builder {
        private int book = -1;
        private int type = -1;
        private int action = -1;
        private int price = -1;
        private int volume = -1;

        public Builder() {
        }

        public Builder setBook(int value) {
            book = value;
            return this;
        }

        public Builder setType(int value) {
            type = value;
            return this;
        }

        public Builder setAction(int value) {
            action = value;
            return this;
        }

        public Builder setPrice(int value) {
            price = value;
            return this;
        }

        public Builder setVolume(int value) {
            volume = value;
            return this;
        }

        public Bid build() {
            return new Bid(this);
        }
    }
}
