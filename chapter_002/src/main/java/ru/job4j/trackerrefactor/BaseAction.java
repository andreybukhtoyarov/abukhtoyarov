package ru.job4j.trackerrefactor;

public abstract class BaseAction implements UserAction {
    private final int key;
    private final String info;

    public BaseAction(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.info);
    }
}
