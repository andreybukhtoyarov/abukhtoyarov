package ru.job4j.nonblockingalgoritm;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NonBlockingCacheTest {

    private NonBlockingCache nbc;

    private ExecutorService pool;

    @Before
    public void setPool() {
        pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    @Before
    public void setNbc() {
        nbc = new NonBlockingCache();
        IntStream.range(0, 4).forEach(
                (x) -> nbc.add(
                        new Base(x, String.format("Name %s", x))
                )
        );
    }

    @Test
    public void whenThrowException() {
        AtomicReference<Exception> exs = new AtomicReference<>();
        IntStream.range(0, 4).forEach(
                (x) -> pool.submit(
                        () -> {
                            try {
                                nbc.update(new Base(1, "Name 1"),
                                        (k, v) -> {
                                            v.setName("New Name");
                                            return v;
                                        });
                            } catch (Exception ex) {
                                exs.set(ex);
                            }
                        }
                )
        );
        pool.shutdown();
        boolean ready = true;
        while (ready) {
            if (pool.isTerminated()) {
                ready = false;
                assertThat(exs.get().getMessage(), is("Versions do not match"));
            }
        }
    }

    @Test
    public void whenDeleteThenSizeIzThree() {
        int before = nbc.size();
        nbc.delete(new Base(2, "Name 2"));
        assertThat(before, is(4));
        assertThat(nbc.size(), is(3));
    }
}
