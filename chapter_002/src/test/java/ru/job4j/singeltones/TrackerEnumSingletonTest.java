package ru.job4j.singeltones;

import org.junit.Test;
import ru.job4j.trackerrefactor.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerEnumSingletonTest {

    @Test
    public void whenGetTwoInstanceThenTheyLincEquals() {
        Tracker trackerOne = TrackerEnumSingleton.INSTANCE.getTracker();
        Tracker trackerTwo = TrackerEnumSingleton.INSTANCE.getTracker();
        Tracker trackerThree = TrackerEnumSingleton.INSTANCE.getTracker();
        assertThat(trackerOne == trackerTwo && trackerOne == trackerThree, is(true));
    }

}