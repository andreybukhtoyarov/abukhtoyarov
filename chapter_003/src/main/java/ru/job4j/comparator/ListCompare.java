package ru.job4j.comparator;

import java.util.Comparator;
import java.util.List;

/**
 * Comparator for list.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class

ListCompare implements Comparator<List<Integer>> {

    @Override
    public int compare(List<Integer> o1, List<Integer> o2) {
        int longest = Integer.compare(o1.size(), o2.size());
        int difference = 0;
        if (longest == 0) {
            for (int index = 0; index < o1.size(); ++index) {
                difference += (Integer.compare(o1.get(index), o2.get(index))) * Math.abs(o1.get(index) - o2.get(index));
            }
        }
        return longest != 0 ? longest : difference;
    }
}
