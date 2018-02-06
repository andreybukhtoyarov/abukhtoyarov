package ru.job4j.productivity;

import java.util.Collection;
import java.util.TreeSet;

public class ProductivityCollection {

    public long add(Collection<String> collection, int amount) {
        long timeStart = System.currentTimeMillis();

        for (int index = 0; index < amount; ++index) {
            collection.add(String.valueOf(index));
        }

        return System.currentTimeMillis() - timeStart;
    }

    public long delete(Collection<String> collection, int amount) {
        long timeStart = System.currentTimeMillis();

        for (int index = 0; index < amount; ++index) {
            collection.remove(index);
        }

        return System.currentTimeMillis() - timeStart;
    }

    public long delete(TreeSet<String> set, int amount) {
        long timeStart = System.currentTimeMillis();

        for (int index = 0; index < amount; ++index) {
            set.remove(String.valueOf(index));
        }

        return System.currentTimeMillis() - timeStart;
    }
}
