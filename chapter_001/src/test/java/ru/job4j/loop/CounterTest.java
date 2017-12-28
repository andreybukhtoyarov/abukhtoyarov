package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    @Test
    public void whenRangeBetweenTwoAndElevenThanThirty() {
        // Create new counter..
        Counter counter = new Counter();
        // Check result (counter.add(2, 11) ) and expected.
        assertThat(
                counter.add(2, 11),
                is(30)
        );
    }
}
