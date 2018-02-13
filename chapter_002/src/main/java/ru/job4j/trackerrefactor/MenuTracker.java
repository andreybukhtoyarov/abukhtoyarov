package ru.job4j.trackerrefactor;

import java.util.List;

/**
 * This class is tracker menu and user actions.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class MenuTracker {
    /**Field with system input.*/
    private Input input;
    /**Field with task Tracker.*/
    private Tracker tracker;
    /**Field with array of user actions.*/
    private UserAction[] actions = new UserAction[6];
    /**Field with array of range menu.*/
    private int[] range = {0, 1, 2, 3, 4, 5};

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Getter for range menu.
     * @return - range menu.
     */
    public int[] getRange() {
        return this.range;
    }

    /**
     * This method filled actions array.
     */
    public void fillActions() {
        this.actions[0] = new AddItem(0, "Добавить новую заявку");
        this.actions[1] = new ShowItems(1, "Показать все заявки");
        this.actions[2] = new EditItem(2, "Редактировать заявку");
        this.actions[3] = new DeleteItem(3, "Удалить заявку");
        this.actions[4] = new FindById(4, "Найти заявку по id");
        this.actions[5] = new FindByName(5, "Найти заявку по имени");
    }

    /**
     * This method use action of actions array by key.
     * @param key - key for action.
     */
    public void selectAction(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * This method prints menu.
     */
    public void showMenu() {
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
    }

    /**
     * This class contain method which create task and add it to Tracker.
     */
    private class AddItem extends BaseAction {

        public AddItem(int key, String info) {
            super(key, info);
        }

        /**
         * This method create task and add it to Tracker.
         * @param input - input.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой языки --------------");
            String name = input.ask("Введите название заявки :");
            String description = input.ask("Введите описание заявки :");
            Item item = new Item(name, description, System.currentTimeMillis());
            tracker.add(item);
            System.out.println(String.format(
                    "%s %s %s",
                    "------------ Заявка добавлена, id заявки :", item.getId(), "------------")
            );
        }

    }

    /**
     * This class contain method which find task by id.
     */
    private class FindById extends BaseAction {

        public FindById(int key, String info) {
            super(key, info);
        }

        /**
         * This method find task by id.
         * @param input   - input.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Ищем заявку по id ------------");
            String id = input.ask("Введите id заявки :");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(String.format("%s %s", "id заявки :", item.getId()));
                System.out.println(String.format("%s %s", "Название заявки :", item.getName()));
                System.out.println(String.format("%s %s", "Описание заявки :", item.getDescription()));
            } else {
                System.out.println("------------ Вы ввели не существующий id ------------");
            }
        }
    }

    /**
     * This class contain method which show all task in Tracker.
     */
    private static class ShowItems extends BaseAction {

        public ShowItems(int key, String info) {
            super(key, info);
        }

        /**
         * This method show all task in Tracker.
         * @param input - input.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Все заявки ------------");
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s %s", "id заявки :", item.getId()));
                System.out.println(String.format("%s %s", "Название заявки :", item.getName()));
                System.out.println(String.format("%s %s", "Описание заявки :", item.getDescription()));
                System.out.println(String.format("%s %s %s", "------------ Конец заявки", item.getId(), "------------"));
            }
            System.out.println("------------ Все заявки показаны------------");
        }
    }

    /**
     * This class contain method which delete task by id.
     */
    private static class DeleteItem extends BaseAction {

        public DeleteItem(int key, String info) {
            super(key, info);
        }

        /**
         * This method delete task by id.
         * @param input - input.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаляем заявку по id ------------");
            String id = input.ask("Введите id заявки :");
            boolean failure = true;
            for (Item item : tracker.getAll()) {
                if (id.equals(item.getId())) {
                    tracker.delete(id);
                    System.out.println("------------ Заявка успешно удалена ------------");
                    failure = false;
                    break;
                }
            }
            if (failure) {
                System.out.println("------------ Вы ввели не существующий id ------------");
            }
        }
    }
}

/**
 * This class contain method which edit task by id.
 */
class EditItem extends BaseAction {

    public EditItem(int key, String info) {
        super(key, info);
    }

    /**
     * This method edit task by id.
     * @param input - input.
     * @param tracker - tracker.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Редактируем заявку по id ------------");
        String id = input.ask("Введите id заявки :");
        boolean failure = true;
        for (Item item : tracker.getAll()) {
            if (item.getId().equals(id)) {
                String name = input.ask("Введите новое имя заявки :");
                String description = input.ask("Введите новое описание заявки :");
                Item itemNew = new Item(name, description, System.currentTimeMillis());
                tracker.replace(id, itemNew);
                System.out.println("------------ Заявка успешно отредактирована ------------");
                failure = false;
                break;
            }
            if (failure) {
                System.out.println("------------ Вы ввели не существующий id ------------");
            }
        }
    }
}

/**
 * This class contain method find task by name.
 */
class FindByName extends BaseAction {

    public FindByName(int key, String info) {
        super(key, info);
    }

    /**
     * This method find task by name.
     * @param input - input.
     * @param tracker - tracker.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Ищем заявку по имени ------------");
        String name = input.ask("Введите имя заявки :");
        List<Item> items = tracker.findByName(name);
        if (items != null) {
            for (Item item : items) {
                System.out.println(String.format("%s %s", "id заявки :", item.getId()));
                System.out.println(String.format("%s %s", "Название заявки :", item.getName()));
                System.out.println(String.format("%s %s", "Описание заявки :", item.getDescription()));
                System.out.println("------------ Конец заявки ------------");
            }
        } else {
            System.out.println("------------ Вы ввели не существующее имя ------------");
        }
    }
}
