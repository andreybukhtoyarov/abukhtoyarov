package ru.job4j.convert;

import java.util.*;

/**
 * Conversion array to list and list to array.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ConvertList {
    /**
     * Conversion array to list.
     * @param array - array that needs to be converted to a list
     * @return array int[][].
     */
    public List<Integer> toList(int[][] array) {
        ArrayList<Integer> list = null;
        if (array != null) {
            list = new ArrayList<>();
            for (int[] arrayLine : array) {
                for (int value : arrayLine) {
                    list.add(value);
                }
            }
        }
        return list;
    }

    /**
     * Conversion list to array.
     * @param list - list that needs to be converted to a array.
     * @param rows - number of rows.
     * @return - ArrayList.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] result = null;
        if (list.size() >= rows && list.size() % rows > 0) {
            result = new int[rows][list.size() / rows + 1];
        } else if (list.size() >= rows && list.size() % rows == 0) {
            result = new int[rows][list.size() / rows];
        }
        if (result != null) {
            Iterator<Integer> iter = list.iterator();
            for (int indexColumn = 0; indexColumn < result.length; ++indexColumn) {
                for (int index = 0; index < result[0].length; ++index) {
                    if (iter.hasNext()) {
                        result[indexColumn][index] = iter.next();
                    }
                }
            }
        }
        return result;
    }
}
