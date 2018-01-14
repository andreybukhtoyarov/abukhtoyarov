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
public class TrackerTest {
    @Test
    public void whenDeleteByIdThenDeleteElement() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("One", "First_Item", 1));
        tracker.add(new Item("Two", "Second_Item", 2));
        tracker.add(new Item("Three", "Third_Item", 3));
        tracker.add(new Item("Four", "Fourth_Item", 4));

        Item[] itemsTest = tracker.findAll();

        String idOne = itemsTest[0].getId();
        String idTwo = itemsTest[1].getId();
        String idThree = itemsTest[2].getId();
        String idFour = itemsTest[3].getId();

        tracker.delete(idThree);

        Item[] expected = new Item[3];
        expected[0] = new Item("One", "First_Item", 1);
        expected[1] = new Item("Two", "Second_Item", 2);
        expected[2] = new Item("Four", "Fourth_Item", 4);

        expected[0].setId(idOne);
        expected[1].setId(idTwo);
        expected[2].setId(idFour);

        Item[] result = tracker.findAll();

        assertThat(
                expected[0].getDescription(),
                is(result[0].getDescription())
        );
        assertThat(
                expected[1].getDescription(),
                is(result[1].getDescription())
        );
        assertThat(
                expected[2].getDescription(),
                is(result[2].getDescription())
        );

        assertThat(
                expected.length,
                is(result.length)
        );
    }

    @Test
    public void whenTrackerAddItemThenAddItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name", "Description", 1234);
        tracker.add(item);

        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceElementsThenReplacedElements() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("One", "One description", 1);
        Item itemTwo = new Item("Two", "Two description", 2);

        tracker.add(itemOne);

        tracker.replace(tracker.findAll()[0].getId(), itemTwo);

        assertThat(tracker.findAll()[0], is(itemTwo));
    }
}
