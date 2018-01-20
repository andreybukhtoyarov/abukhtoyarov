package ru.job4j.tracker;

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
    private final String beforeAndAfter = new StringJoiner(System.lineSeparator())
            .add("Меню:").add("0. Добавить новую заявку").add("1. Показать все заявки").add("2. Редактировать заявку")
            .add("3. Удалить заявку").add("4. Найти заявку по id").add("5. Найти заявку по имени").add("6. Выйти из программы")
            .add("Введите пункт меню : ").toString();

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
        Input stubInput = new StubInput(new String[] {"0", "nameTEST", "descriptionTEST", "6"});
        StartUI start = new StartUI(stubInput, this.tracker);
        start.init();

        assertThat(this.tracker.getAll()[3].getName(), is("nameTEST"));
        assertThat(this.tracker.getAll()[3].getDescription(), is("descriptionTEST"));
    }

    @Test
    public void whenUserEditTaskThenTaskInTrackerChanged() {
        Input stubInput = new StubInput(new String[] {"2", this.tracker.getAll()[1].getId(), "nameTEST", "descriptionTEST", "6"});
        StartUI start = new StartUI(stubInput, this.tracker);
        start.init();

        assertThat(this.tracker.getAll()[1].getName(), is("nameTEST"));
        assertThat(this.tracker.getAll()[1].getDescription(), is("descriptionTEST"));
    }

    @Test
    public void whenUserDeleteTaskThenTrackerDeleteTask() {
        Input stubInput = new StubInput(new String[] {"3", this.tracker.getAll()[1].getId(), "6"});
        StartUI start = new StartUI(stubInput, this.tracker);
        start.init();

        assertThat(this.tracker.getAll().length, is(2));
        assertThat(this.tracker.getAll()[0].getName(), is("NameOne"));
        assertThat(this.tracker.getAll()[1].getDescription(), is("DescTwo"));
    }

    @Test
    public void whenUserShowAllThenShowAll() {
        Input stubInput = new StubInput(new String[] {"1", "6"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(this.beforeAndAfter)
                .add("------------ Все заявки ------------")
                .add("id заявки : " + this.tracker.getAll()[0].getId())
                .add("Название заявки : " + this.tracker.getAll()[0].getName())
                .add("Описание заявки : " + this.tracker.getAll()[0].getDescription())
                .add("------------ Конец заявки " + this.tracker.getAll()[0].getId() + " ------------")
                .add("id заявки : " + this.tracker.getAll()[1].getId())
                .add("Название заявки : " + this.tracker.getAll()[1].getName())
                .add("Описание заявки : " + this.tracker.getAll()[1].getDescription())
                .add("------------ Конец заявки " + this.tracker.getAll()[1].getId() + " ------------")
                .add("id заявки : " + this.tracker.getAll()[2].getId())
                .add("Название заявки : " + this.tracker.getAll()[2].getName())
                .add("Описание заявки : " + this.tracker.getAll()[2].getDescription())
                .add("------------ Конец заявки " + this.tracker.getAll()[2].getId() + " ------------")
                .add("------------ Все заявки показаны------------")
                .add(this.beforeAndAfter + "\n")
                .toString();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserFindByIdThenShowTaskWithId() {
        Input stubInput = new StubInput(new String[] {"4", this.tracker.getAll()[1].getId(), "6"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(beforeAndAfter)
                .add("------------ Ищем заявку по id ------------")
                .add("Введите id заявки :")
                .add("id заявки : " + this.tracker.getAll()[1].getId())
                .add("Название заявки : " + this.tracker.getAll()[1].getName())
                .add("Описание заявки : " + this.tracker.getAll()[1].getDescription())
                .add(beforeAndAfter + "\n")
                .toString();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserFindByIdAndIdNotExistThenShowIdNotExist() {
        Input stubInput = new StubInput(new String[] {"4", "43343434", "6"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(beforeAndAfter)
                .add("------------ Ищем заявку по id ------------")
                .add("Введите id заявки :")
                .add("------------ Вы ввели не существующий id ------------")
                .add(beforeAndAfter + "\n")
                .toString();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserFindByNameThenShowTaskWithName() {
        Input stubInput = new StubInput(new String[] {"5", "Name", "6"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(beforeAndAfter)
                .add("------------ Ищем заявку по имени ------------")
                .add("Введите имя заявки :")
                .add("id заявки : " + this.tracker.getAll()[1].getId())
                .add("Название заявки : " + this.tracker.getAll()[1].getName())
                .add("Описание заявки : " + this.tracker.getAll()[1].getDescription())
                .add("------------ Конец заявки ------------")
                .add(beforeAndAfter + "\n")
                .toString();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserFindByNameAndNameNotExistThenShowNameNotExist() {
        Input stubInput = new StubInput(new String[] {"5", "NameNotExist", "6"});
        new StartUI(stubInput, this.tracker).init();

        String expected = new StringJoiner(System.lineSeparator())
                .add(beforeAndAfter)
                .add("------------ Ищем заявку по имени ------------")
                .add("Введите имя заявки :")
                .add("------------ Вы ввели не существующее имя ------------")
                .add(beforeAndAfter + "\n")
                .toString();
        assertThat(out.toString(), is(expected));
    }
}
