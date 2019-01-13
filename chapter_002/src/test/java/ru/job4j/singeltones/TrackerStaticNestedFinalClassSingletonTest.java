package ru.job4j.singeltones;

import org.junit.Test;
import ru.job4j.trackerrefactor.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerStaticNestedFinalClassSingletonTest {

    @Test
    public void whenGetTwoInstanceThenTheyLincEquals() {
        Tracker trackerOne = TrackerStaticNestedFinalClassSingleton.getInstance();
        Tracker trackerTwo = TrackerStaticNestedFinalClassSingleton.getInstance();
        Tracker trackerThree = TrackerStaticNestedFinalClassSingleton.getInstance();
        assertThat(trackerOne == trackerTwo && trackerOne == trackerThree, is(true));
    }

}