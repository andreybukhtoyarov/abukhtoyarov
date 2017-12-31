package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class FindLoopTest {
	@Test
	public void whenArraySevenThreeNineAndValueThreeThenOne() {
		FindLoop findLoop = new FindLoop();
		int[] array = {7, 3, 9};
		assertThat(findLoop.indexOf(array, 3),
				is(1)
		);
	}
	
	@Test
	public void whenArraySevenThreeNineAndValueTwoThenMinusOne() {
		FindLoop findLoop = new FindLoop();
		int[] array = {7, 3, 9};
		assertThat(findLoop.indexOf(array, 2),
				is(-1)
		);
	}
}
	