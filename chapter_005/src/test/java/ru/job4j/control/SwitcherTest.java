package ru.job4j.control;

import org.junit.Test;

import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SwitcherTest {

    private final StringHolder sh = new StringHolder();

    private final Switcher sw = new Switcher(sh);

    private final ExecutorService pool = Executors.newFixedThreadPool(2);

    @Test
    public void when() {
        pool.submit(new AddOn(sw, 2, 10));
        pool.submit(new AddOn(sw, 1, 10));
        pool.shutdown();
        try {
            pool.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {

        }
        assertThat(sh.get(),
                is(new StringBuilder().append("1111111111").append("2222222222")
                        .append("1111111111").append("2222222222")
                        .append("1111111111").append("2222222222")
                        .append("1111111111").append("2222222222")
                        .append("1111111111").append("2222222222")
                        .append("1111111111").append("2222222222")
                        .append("1111111111").append("2222222222")
                        .append("1111111111").append("2222222222")
                        .append("1111111111").append("2222222222")
                        .append("1111111111").append("2222222222")
                        .toString()
                ));
    }
}
