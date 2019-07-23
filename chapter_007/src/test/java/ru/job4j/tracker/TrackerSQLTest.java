package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.trackerrefactor.Item;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class TrackerSQLTest {

    @Test
    public void whenThen() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL()) {
            System.out.println(tracker.init());
            //tracker.add(new Item("Name 1", "Description 1", System.currentTimeMillis()));
            //tracker.replace("4", new Item("NEW Name", "NEW Description", System.currentTimeMillis()));
            //tracker.delete("2");
            /*List<Item> items = tracker.getAll();
            for (Item item : items) {
                System.out.println(item.getId());
                System.out.println(item.getName());
                System.out.println(item.getDescription());
                System.out.println(item.getCreate());
            }*/
            List<Item> names = tracker.findByName("Name 1");
            for (Item item : names) {
                System.out.println(item.getId());
                System.out.println(item.getName());
                System.out.println(item.getDescription());
                System.out.println(item.getCreate());
            }
        }
    }
}
