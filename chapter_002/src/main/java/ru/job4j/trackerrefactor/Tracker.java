package ru.job4j.trackerrefactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * This class wrapper over an array.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Tracker {
    /**Field array of items.*/
    private List<Item> items = new ArrayList<>();

    /**
     * This method add item in tracker.
     * @param item - task to be added to the Tracker.
     * @return task to be added to the Tracker.
     */
    public Item add(Item item) {
        item.setId(this.generateId() + item.getCreate());
        this.items.add(item);
        return item;
    }

    /**
     * This method create unique id.
     * @return unique id.
     */
    private String generateId() {
        return String.valueOf(new Random().nextInt());
    }

    /**
     * This method changes the element from id to the specified element.
     * @param id - id of the element to be replaced.
     * @param item - new element.
     */
    public void replace(String id, Item item) {
        if (this.items != null) {
            find(this.items, id,
                    (x) -> {
                        item.setId(this.generateId() + item.getCreate());
                        this.items.set(this.items.indexOf(x), item);
                        return true;
                    }
            );
        }
    }

    private void find(final List<Item> items, final String id, final Predicate<Item> pred) {
        for (Item it : items) {
            if (id.equals(it.getId())) {
                pred.test(it);
                break;
            }
        }
    }

    private void find(final String name, final List<Item> items, final Predicate<Item> pred) {
        for (Item it : items) {
            if (name.equals(it.getName())) {
                pred.test(it);
                break;
            }
        }
    }

    /**
     * This method delete elements of the array by id.
     * @param id - id element to delete.
     */
    public void delete(String id) {
        if (!this.items.isEmpty()) {
            find(this.items, id,
                    (x) -> {
                        this.items.remove(x);
                        return true;
                    }
            );
        }
    }

    /**
     * This method create array of all array elements Tracker.
     * @return array of all array elements Tracker.
     */
    public List<Item> getAll() {
        return new ArrayList<>(this.items);
    }


    /**
     * This method find array element by Name.
     * @param key - name of Item.
     * @return array of Item with the same name.
     */
    public List<Item> findByName(String key) {
        List<Item> result = null;
        if (!this.items.isEmpty()) {
            ArrayList<Item> tmp = new ArrayList<>();
            find(key, this.items,
                    (x) -> {
                        tmp.add(x);
                        return true;
                    }
            );
            if (!tmp.isEmpty()) {
                result = new ArrayList<>(tmp);
            }
        }
        return result;
    }

    /**
     * This method find Item by id.
     * @param id - id of Item.
     * @return Item.
     */
    public Item findById(String id) {
        final Item[] result = new Item[1];
        if (!this.items.isEmpty()) {
            find(this.items, id,
                    (x) -> {
                        result[0] = x;
                        return true;
                    }
            );
        }
        return result[0];
    }
}
