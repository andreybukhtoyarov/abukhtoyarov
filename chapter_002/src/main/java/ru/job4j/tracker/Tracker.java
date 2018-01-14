package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * This class wrapper over an array.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Tracker {
    /**Field array of items.*/
    private Item[] items = new Item[100];
    /**Field count array elements.*/
    private int index = 0;

    /**
     * This method add item in tracker.
     * @param item - application to be added to the Tracker.
     * @return application to be added to the Tracker.
     */
    public Item add(Item item) {
        item.setId(this.generateId() + item.getCreate());
        this.items[index++] = item;
        return item;
    }

    /**
     * This method create unique id.
     * @return unique id.
     */
    private String generateId() {
        return String.valueOf(new Random(67).nextLong());
    }

    /**
     * This method changes the element from id to the specified element.
     * @param id - id of the element to be replaced.
     * @param item - new element.
     */
    public void replace(String id, Item item) {
        for (int index = 0; this.items != null && index < this.items.length; ++index) {
            if (this.items[index].getId().equals(id)) {
                this.items[index] = item;
                break;
            }
        }
    }

    /**
     * This method delete elements of the array by id.
     * @param id - id element to delete.
     */
    public void delete(String id) {
        int index = 0;
        for (; this.items != null && index < this.items.length; ++index) {
            if (this.items[index].getId().equals(id))
                break;
        }
        Item[] tmp = new Item[this.items.length];
        System.arraycopy(this.items, 0, tmp, 0, index);
        System.arraycopy(this.items, index + 1, tmp, index, this.items.length - index - 1);
        items = Arrays.copyOf(tmp, tmp.length);
        this.index--;
    }

    /**
     * This method create array of all array elements Tracker.
     * @return array of all array elements Tracker.
     */
    public Item[] getAll(){
        Item[] item = Arrays.copyOf(this.items, this.index);
        return item;
    }

    /**
     * This method find array element by Name.
     * @param key - name of Item.
     * @return array of Item with the same name.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.items.length];
        int indexResult = 0;
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                result[indexResult++] = item;
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
        Item result = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
