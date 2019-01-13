package ru.job4j.singeltones;

import org.junit.Test;
import ru.job4j.trackerrefactor.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerFinalStaticSingletonTest {

    @Test
    public void whenGetTwoInstanceThenTheyLincEquals() {
        Tracker trackerOne = TrackerFinalStaticSingleton.getInstance();
        Tracker trackerTwo = TrackerFinalStaticSingleton.getInstance();
        Tracker trackerThree = TrackerFinalStaticSingleton.getInstance();
        assertThat(trackerOne == trackerTwo && trackerOne == trackerThree, is(true));
    }

}