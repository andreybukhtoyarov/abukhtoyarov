package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNameAndDesc() {
        Tracker tracker = new Tracker();

        Input stubInput = new StubInput(new String[] {"0", "name", "description", "6"});
        StartUI start = new StartUI(stubInput, tracker);
        start.init();

        assertThat(tracker.getAll()[0].getName(), is("name"));
        assertThat(tracker.getAll()[0].getDescription(), is("description"));
    }

    @Test
    public void whenUserEditTaskThenTaskInTrackerChanged() {
        Tracker tracker = new Tracker();

        Item itemOne = new Item();
        Item itemTwo = new Item();
        Item item = new Item();

        tracker.add(itemOne);
        tracker.add(item);
        tracker.add(itemTwo);

        Input stubInput = new StubInput(new String[] {"2", tracker.getAll()[1].getId(), "name", "description", "6"});
        StartUI start = new StartUI(stubInput, tracker);
        start.init();

        assertThat(tracker.getAll()[1].getName(), is("name"));
        assertThat(tracker.getAll()[1].getDescription(), is("description"));
    }

    @Test
    public void whenUserDeleteTaskThenTrackerDeleteTask() {
        Tracker tracker = new Tracker();

        Item itemOne = new Item("NameOne", "DescOne", System.currentTimeMillis());
        Item itemTwo = new Item("NameTwo", "DescTwo", System.currentTimeMillis());
        Item item = new Item("Name", "Desc", System.currentTimeMillis());

        tracker.add(itemOne);
        tracker.add(item);
        tracker.add(itemTwo);

        Input stubInput = new StubInput(new String[] {"3", tracker.getAll()[1].getId(), "6"});
        StartUI start = new StartUI(stubInput, tracker);
        start.init();

        assertThat(tracker.getAll().length, is(2));
        assertThat(tracker.getAll()[0].getName(), is("NameOne"));
        assertThat(tracker.getAll()[1].getDescription(), is("DescTwo"));
    }
}
