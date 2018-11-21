package ru.job4j.pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    private ThreadPool pool;

    private List<Runnable> workList;

    private long count = 0;
    private long countTwo = 0;

    private  String str = "";
    private  String strTwo = "";
    private  String strThree = "";

    private Runnable one = () -> count = IntStream.range(0, 5).count();
    private Runnable two = () -> countTwo = IntStream.range(0, 5).count();

    private Runnable three = () -> {
        StringBuilder sb = new StringBuilder();
        IntStream.range(20, 25).forEachOrdered(sb::append);
        str = sb.toString();
    };

    private Runnable four = () -> {
        StringBuilder sb = new StringBuilder();
        IntStream.range(20, 25).forEachOrdered(sb::append);
        strTwo = sb.toString();
    };

    private Runnable five = () -> {
        StringBuilder sb = new StringBuilder();
        IntStream.range(20, 25).forEachOrdered(sb::append);
        strThree = sb.toString();
    };

    @Before
    public void setWorkList() {
        workList = new ArrayList<>();
        workList.add(one);
        workList.add(two);
        workList.add(three);
        workList.add(four);
        workList.add(five);
    }

    @Before
    public void setPool() {
        pool = new ThreadPool();
    }

    @After
    public void back() {
        count = 0;
        countTwo = 0;
        str = "";
        strTwo = "";
        strThree = "";
    }

    @Test
    public void whenRunThenCountAndStringsIsCorrect() {
        workList.forEach(pool::work);
        pool.joinPool();
        pool.shutdown();
        assertThat(count, is(5L));
        assertThat(countTwo, is(5L));
        assertThat(str, is("2021222324"));
        assertThat(strTwo, is("2021222324"));
        assertThat(strThree, is("2021222324"));
    }

    @Test (expected = NullPointerException.class)
    public void whenShutDownThenNPE() {
        workList.forEach(pool::work);
        pool.joinPool();
        pool.shutdown();
        workList.forEach(pool::work);
        pool.joinPool();
    }
}
