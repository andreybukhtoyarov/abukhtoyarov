package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@since 28.12.2017.
 */
public class MaxTest {
    @Test
    public void whenFirstEqualsTwoAndSecondEqualsOneThenReturnFirst() {
        Max max = new Max();
        assertThat(max.max(2, 1), is(2));
    }

    @Test
    public void whenFirstEqualsOneAndSecondEqualsThreeReturnSecond() {
        Max max = new Max();
        assertThat(max.max(1, 3), is(3));
    }

    @Test
    public void whenFirstNineSecondFiveThirdFourThenReturnFirst() {
        Max max = new Max();
        assertThat(max.max(9, 5, 4), is(9));
    }
}
