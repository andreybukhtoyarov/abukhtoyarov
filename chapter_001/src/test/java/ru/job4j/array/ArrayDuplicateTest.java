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
public class ArrayDuplicateTest {
	@Test
	public void whenArrayHasDuplicateThenDropIt() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();

		String[] array = {"Olia", "Masha", "Olia", "Sveta", "Sveta", "Darya"};
		String[] expected = {"Olia", "Masha", "Darya", "Sveta"};
		String[] result = arrayDuplicate.remove(array);

		assertThat(
				result,
				is(expected)
		);
	}

	@Test
	public void whenArrayHasDuplicateAllThenDropIt() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();

		String[] array = {"Darya", "Darya", "Darya", "Darya", "Darya", "Darya"};
		String[] expected = {"Darya"};
		String[] result = arrayDuplicate.remove(array);

		assertThat(
				result,
				is(expected)
		);
	}
}
