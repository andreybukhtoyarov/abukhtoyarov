package ru.job4j.departmentssort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sort {

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

    public String[] ascendingSort(String[] array) {
        String[] addedDepartments = addDepartments(array);
        Arrays.sort(addedDepartments, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int lengthOne = o1.split("/").length;
                int lengthTwo = o2.split("/").length;
                int comp = 0;
                for (int index = 0; index < (lengthOne < lengthTwo ? lengthOne : lengthTwo); ++index) {
                    comp = o1.split("/")[index].compareTo(o2.split("/")[index]);
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

    public String[] descendingSort(String[] array) {
        String[] addedDepartments = addDepartments(array);
        Arrays.sort(addedDepartments, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int lengthOne = o1.split("/").length;
                int lengthTwo = o2.split("/").length;
                int comp = 0;
                for (int index = 0; index < (lengthOne < lengthTwo ? lengthOne : lengthTwo); ++index) {
                    comp = o2.split("/")[index].compareTo(o1.split("/")[index]);
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
}
