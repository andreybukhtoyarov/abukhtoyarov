package ru.job4j.trackerrefactor;

import java.util.List;

public interface ITracker {
    Item add(Item item);
    void replace(String id, Item item);
    void delete(String id);
    List<Item> getAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
