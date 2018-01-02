package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class BubbleSortTest {
	@Test
	public void whenSortArrayWithNineElementsThenSortedArray() {
		BubbleSort bubbleSort = new BubbleSort();
		int[] array = {4, 8, 1, 7, 2, 0, 5, 3, 23};

		int[] result = bubbleSort.sort(array);
		int[] expected = {0, 1, 2, 3, 4, 5, 7, 8, 23};

		assertThat(
				result,
				is(expected)
		);
	}

	@Test
	public void whenSortArrayWithEightElementsThenSortedArray() {
		BubbleSort bubbleSort = new BubbleSort();
		int[] array = {4, 8, 1, 7, 2, 5, 3, 23};

		int[] result = bubbleSort.sort(array);
		int[] expected = {1, 2, 3, 4, 5, 7, 8, 23};

		assertThat(
				result,
				is(expected)
		);
	}
}
