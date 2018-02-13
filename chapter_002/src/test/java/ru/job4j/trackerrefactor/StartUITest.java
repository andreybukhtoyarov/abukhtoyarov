package ru.job4j.trackerrefactor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StartUITest {
    private Tracker tracker = null;
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final String before = new StringJoiner(System.lineSeparator())
            .add("0. Добавить новую заявку").add("1. Показать все заявки").add("2. Редактировать заявку")
            .add("3. Удалить заявку").add("4. Найти заявку по id").add("5. Найти заявку по имени")
            .add("Введите пункт меню : ").toString();
    private final String after = "Выйти? y или n : \n";

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }

    @Before
    public void setTracker() {
        this.tracker = new Tracker();
        Item itemOne = new Item("NameOne", "DescOne", System.currentTimeMillis());
        Item itemTwo = new Item("NameTwo", "DescTwo", System.currentTimeMillis());
        Item item = new Item("Name", "Desc", System.currentTimeMillis());
        this.tracker.add(itemOne);
        this.tracker.add(item);
        this.tracker.add(itemTwo);
    }

    @After
    public void backOutput() {
        System.setOut(stdOut);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNameAndDesc() {
        Input stubInput = new StubInput(new String[] {"0", "nameTEST", "descriptionTEST", "y"});
        StartUI start = new StartUI(stubInput, this.tracker);
        start.init();

        assertThat(this.tracker.getAll().get(3).getName(), is("nameTEST"));
        assertThat(this.tracker.getAll().get(3).getDescription(), is("descriptionTEST"));
    }

    @Test
    public void whenUserEditTaskThenTaskInTrackerChanged() {
        Input stubInput = new StubInput(new String[] {"2", this.tracker.getAll().get(1).getId(), "nameTEST", "descriptionTEST", "y"});
        StartUI start = new StartUI(stubInput, this.tracker);
        start.init();

        assertThat(this.tracker.getAll().get(1).getName(), is("nameTEST"));
        assertThat(this.tracker.getAll().get(1).getDescription(), is("descriptionTEST"));
    }

    @Test
    public void whenUserDeleteTaskThenTrackerDeleteTask() {
        Input stubInput = new StubInput(new String[] {"3", this.tracker.getAll().get(1).getId(), "y"});
        StartUI start = new StartUI(stubInput, this.tracker);
        start.init();

        assertThat(this.tracker.getAll().size(), is(2));
        assertThat(this.tracker.getAll().get(0).getName(), is("NameOne"));
        assertThat(this.tracker.getAll().get(1).getDescription(), is("DescTwo"));
    }

    @Test
    public void whenUserShowAllThenShowAll() {
        Input stubInput = new StubInput(new String[] {"1", "y"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(this.before)
                .add("------------ Все заявки ------------")
                .add(String.format("%s %s", "id заявки :", this.tracker.getAll().get(0).getId()))
                .add(String.format("%s %s", "Название заявки :", this.tracker.getAll().get(0).getName()))
                .add(String.format("%s %s", "Описание заявки :", this.tracker.getAll().get(0).getDescription()))
                .add(String.format("%s %s %s", "------------ Конец заявки", this.tracker.getAll().get(0).getId(), "------------"))
                .add(String.format("%s %s", "id заявки :", this.tracker.getAll().get(1).getId()))
                .add(String.format("%s %s", "Название заявки :", this.tracker.getAll().get(1).getName()))
                .add(String.format("%s %s", "Описание заявки :", this.tracker.getAll().get(1).getDescription()))
                .add(String.format("%s %s %s", "------------ Конец заявки", this.tracker.getAll().get(1).getId(), "------------"))
                .add(String.format("%s %s", "id заявки :", this.tracker.getAll().get(2).getId()))
                .add(String.format("%s %s", "Название заявки :", this.tracker.getAll().get(2).getName()))
                .add(String.format("%s %s", "Описание заявки :", this.tracker.getAll().get(2).getDescription()))
                .add(String.format("%s %s %s", "------------ Конец заявки", this.tracker.getAll().get(2).getId(), "------------"))
                .add("------------ Все заявки показаны------------")
                .add(after)
                .toString();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserFindByIdThenShowTaskWithId() {
        Input stubInput = new StubInput(new String[] {"4", this.tracker.getAll().get(1).getId(), "y"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(before)
                .add("------------ Ищем заявку по id ------------")
                .add("Введите id заявки :")
                .add(String.format("%s %s", "id заявки :", this.tracker.getAll().get(1).getId()))
                .add(String.format("%s %s", "Название заявки :", this.tracker.getAll().get(1).getName()))
                .add(String.format("%s %s", "Описание заявки :", this.tracker.getAll().get(1).getDescription()))
                .add(after)
                .toString();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserFindByIdAndIdNotExistThenShowIdNotExist() {
        Input stubInput = new StubInput(new String[] {"4", "43343434", "y"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(before)
                .add("------------ Ищем заявку по id ------------")
                .add("Введите id заявки :")
                .add("------------ Вы ввели не существующий id ------------")
                .add(after)
                .toString();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserFindByNameThenShowTaskWithName() {
        Input stubInput = new StubInput(new String[] {"5", "Name", "y"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(before)
                .add("------------ Ищем заявку по имени ------------")
                .add("Введите имя заявки :")
                .add(String.format("%s %s", "id заявки :", this.tracker.getAll().get(1).getId()))
                .add(String.format("%s %s", "Название заявки :", this.tracker.getAll().get(1).getName()))
                .add(String.format("%s %s", "Описание заявки :", this.tracker.getAll().get(1).getDescription()))
                .add("------------ Конец заявки ------------")
                .add(after)
                .toString();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserFindByNameAndNameNotExistThenShowNameNotExist() {
        Input stubInput = new StubInput(new String[] {"5", "NameNotExist", "y"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(before)
                .add("------------ Ищем заявку по имени ------------")
                .add("Введите имя заявки :")
                .add("------------ Вы ввели не существующее имя ------------")
                .add(after)
                .toString();
        assertThat(out.toString(), is(expected));
    }
}
