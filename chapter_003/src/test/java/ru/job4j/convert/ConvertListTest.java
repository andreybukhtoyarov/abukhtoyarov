package ru.job4j.convert;

import org.junit.Test;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListTest {
    @Test
    public void whenToListThenNewList() {
        ConvertList conversion = new ConvertList();
        ArrayList<Integer> expected = new ArrayList<>(6);
        for (int value = 1; value < 7; ++value) {
            expected.add(value);
        }
        int[][] array = new int[][] {{1,2},{3,4},{5,6}};
        assertThat(conversion.toList(array), is(expected));
    }

    @Test
    public void whenToArrayThenNewArray() {
        ConvertList conversion = new ConvertList();
        ArrayList<Integer> list = new ArrayList<>(6);
        for (int value = 1; value < 7; ++value) {
            list.add(value);
        }
        int[][] expected = new int[][] {{1,2},{3,4},{5,6}};
        assertThat(conversion.toArray(list, 3), is(expected));
    }

    @Test
    public void whenRowsAndListSizeNotMultipleToArrayThenNewArrayWithZerosInTail() {
        ConvertList conversion = new ConvertList();
        ArrayList<Integer> list = new ArrayList<>(5);
        for (int value = 1; value < 6; ++value) {
            list.add(value);
        }
        int[][] expected = new int[][] {{1,2},{3,4},{5,0}};
        assertThat(conversion.toArray(list, 3), is(expected));
    }
}
