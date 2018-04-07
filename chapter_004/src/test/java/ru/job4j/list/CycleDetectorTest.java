package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CycleDetectorTest {
    private Node<Integer> four = new Node<>(4, null);
    private Node<Integer> three = new Node<>(3, four);
    private Node<Integer> two = new Node<>(2, three);
    private Node<Integer> one = new Node<>(1, two);

    @Before
    public void setFour() {
        four.setNext(one);
    }

    @Test
    public void whenMethodFindCycleTheTrue() {
        CycleDetector cd = new CycleDetector();
        assertThat(cd.hasCycle(one), is(true));
    }

    @Test
    public void whenCycleInTheMiddleThenTrue() {
        CycleDetector cd = new CycleDetector();
        three.setNext(two);
        four.setNext(null);
        assertThat(cd.hasCycle(one), is(true));
    }

    @Test
    public void whenCycleIsNotExistThenFalse() {
        CycleDetector cd = new CycleDetector();
        four.setNext(null);
        assertThat(cd.hasCycle(one), is(false));
    }
}