package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FactorialTest {
    @Test
    public void whenFiveThenOneTwenty() {
        // Create new Factorial
        Factorial fact = new Factorial();
        // Check result and expected.
        assertThat(
                fact.calc(5),
                is(120)
        );
    }

    @Test
    public void whenZeroThenOne() {
        // Create new Factorial
        Factorial fact = new Factorial();
        // Check result and expected.
        assertThat(
                fact.calc(0),
                is(1)
        );
    }

    @Test
    public void whenMinusOneThenException() {
        // Create new Factorial
        Factorial fact = new Factorial();
        // Check result and expected.
        assertThat(
                fact.calc(-1),
                is(-1)
        );
    }
}
