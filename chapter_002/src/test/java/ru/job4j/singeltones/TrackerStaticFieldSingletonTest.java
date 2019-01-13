package ru.job4j.singeltones;

import org.junit.Test;
import ru.job4j.trackerrefactor.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerStaticFieldSingletonTest {

    @Test
    public void whenGetTwoInstanceThenTheyLincEquals() {
        Tracker trackerOne = TrackerStaticFieldSingleton.getInstance();
        Tracker trackerTwo = TrackerStaticFieldSingleton.getInstance();
        Tracker trackerThree = TrackerStaticFieldSingleton.getInstance();
        assertThat(trackerOne == trackerTwo && trackerOne == trackerThree, is(true));
    }

}