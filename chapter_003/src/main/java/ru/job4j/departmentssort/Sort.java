package ru.job4j.departmentssort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort departments by separator and adding missing ones.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Sort {
    /**
     * Adding missing ones.
     * @param array - array with list departments.
     * @return augmented array.
     */
    public String[] addDepartments(String[] array) {
        ArrayList<String> departmentsList = new ArrayList<>(Arrays.asList(array));
        for (String line : array) {
            if (line.split("/").length > 1) {
                String[] partitions = new String[line.split("/").length - 1];
                partitions[0] = line.split("/")[0];
                if (!departmentsList.contains(partitions[0])) {
                    departmentsList.add(partitions[0]);
                }
                for (int index = 1; index < line.split("/").length - 1; ++index) {
                    partitions[index] = String.format("%s/%s", partitions[index - 1], line.split("/")[index]);
                    if (!departmentsList.contains(partitions[index])) {
                        departmentsList.add(partitions[index]);
                    }
                }
            }
        }
        return departmentsList.toArray(new String[departmentsList.size()]);
    }

    /**
     * Generalized method for comparison.
     * @param array - array with list departments.
     * @param sorter - comparator.
     * @param splitter - separator.
     * @return sorted array.
     */
    private String[] execute(String[] array, Compare sorter, String splitter) {
        String[] addedDepartments = addDepartments(array);
        Arrays.sort(addedDepartments, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int lengthOne = o1.length();
                int lengthTwo = o2.length();
                int comp = 0;
                for (int index = 0; index < (lengthOne < lengthTwo ? lengthOne : lengthTwo); ++index) {
                    comp = sorter.comparing(o1, o2, index, splitter);
                    if (comp == 0 && lengthOne != lengthTwo) {
                        comp = lengthOne < lengthTwo ? -1 : 1;
                        break;
                    } else if (comp != 0) {
                        break;
                    }
                }
                return comp;
            }
        });
        return addedDepartments;
    }

    /**
     * Ascending sort.
     * @param array - array with list departments.
     * @return sorted array.
     */
    public String[] ascendingSort(String[] array) {
        return execute(array, new AscendingSort(), "/");
    }

    /**
     * Descending sort.
     * @param array - array with list departments.
     * @return sorted array.
     */
    public String[] descendingSort(String[] array) {
        return execute(array, new DescendingSort(), "/");
    }
}
