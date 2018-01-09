package ru.job4j.control;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@since 06.01.2018.
 */
public class UniteTest {
	@Test
	public void whenFirstOneThreeFourNineSecondOneTwoSixTenThenUnitedArray() {
		Unite unite = new Unite();
		int[] first = {1, 3, 4, 9};
		int[] second = {1, 2, 6, 10};
		int[] expect = {1, 1, 2, 3, 4, 6, 9, 10};
		int[] result = unite.uniteArray(first, second);
		assertThat(
				result,
				is(expect)
		);
	}

	@Test
	public void whenFirstOneThreeSecondOneTwoSixTenThenUnitedArray() {
		Unite unite = new Unite();
		int[] first = {1, 3};
		int[] second = {1, 2, 6, 10};
		int[] expect = {1, 1, 2, 3, 6, 10};
		int[] result = unite.uniteArray(first, second);
		assertThat(
				result,
				is(expect)
		);
	}

	@Test
	public void whenFirstOneThreeFourNineSecondSixTenThenUnitedArray() {
		Unite unite = new Unite();
		int[] first = {1, 3, 4, 9};
		int[] second = {6, 10};
		int[] expect = {1, 3, 4, 6, 9, 10};
		int[] result = unite.uniteArray(first, second);
		assertThat(
				result,
				is(expect)
		);
	}
}
