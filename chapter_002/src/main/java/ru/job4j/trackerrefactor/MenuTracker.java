package ru.job4j.trackerrefactor;

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
        this.actions[0] = new AddItem();
        this.actions[1] = new ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindById();
        this.actions[5] = new FindByName();
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
    private class AddItem implements UserAction {

        @Override
        public int key() {
            return 0;
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
            System.out.println("------------ Заявка добавлена, id заявки : " + item.getId() + " ------------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Добавить новую заявку");
        }
    }

    /**
     * This class contain method which find task by id.
     */
    private class FindById implements UserAction {

        @Override
        public int key() {
            return 4;
        }

        /**
         * This method find task by id.
         * @param input - input.
         * @param tracker - tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Ищем заявку по id ------------");
            String id = input.ask("Введите id заявки :");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("id заявки : " + item.getId());
                System.out.println("Название заявки : " + item.getName());
                System.out.println("Описание заявки : " + item.getDescription());
            } else {
                System.out.println("------------ Вы ввели не существующий id ------------");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по id");
        }
    }

    /**
     * This class contain method which show all task in Tracker.
     */
    private static class ShowItems implements UserAction {

        @Override
        public int key() {
            return 1;
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
                System.out.println("id заявки : " + item.getId());
                System.out.println("Название заявки : " + item.getName());
                System.out.println("Описание заявки : " + item.getDescription());
                System.out.println("------------ Конец заявки " + item.getId() + " ------------");
            }
            System.out.println("------------ Все заявки показаны------------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Показать все заявки");
        }
    }

    /**
     * This class contain method which delete task by id.
     */
    private static class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 3;
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
            Item[] itemsArray = tracker.getAll();

            for (int index = 0; index < itemsArray.length; ++index) {
                if (id.equals(itemsArray[index].getId())) {
                    tracker.delete(id);
                    System.out.println("------------ Заявка успешно удалена ------------");
                    break;
                } else if (index == itemsArray.length - 1) {
                    System.out.println("------------ Вы ввели не существующий id ------------");
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Удалить заявку");
        }
    }
}

/**
 * This class contain method which edit task by id.
 */
class EditItem implements UserAction {

    @Override
    public int key() {
        return 2;
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
        Item[] itemsArray = tracker.getAll();

        for (int index = 0; index < itemsArray.length; ++index) {
            if (itemsArray[index].getId().equals(id)) {
                String name = input.ask("Введите новое имя заявки :");
                String description = input.ask("Введите новое описание заявки :");
                Item itemNew = new Item(name, description, System.currentTimeMillis());
                tracker.replace(id, itemNew);
                System.out.println("------------ Заявка успешно отредактирована ------------");
                break;
            } else if (index == itemsArray.length - 1) {
                System.out.println("------------ Вы ввели не существующий id ------------");
            }
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Редактировать заявку");
    }
}

/**
 * This class contain method find task by name.
 */
class FindByName  implements UserAction {

    @Override
    public int key() {
        return 5;
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
        Item[] items = tracker.findByName(name);
        if (items != null) {
            for (Item item : items) {
                System.out.println("id заявки : " + item.getId());
                System.out.println("Название заявки : " + item.getName());
                System.out.println("Описание заявки : " + item.getDescription());
                System.out.println("------------ Конец заявки ------------");
            }
        } else {
            System.out.println("------------ Вы ввели не существующее имя ------------");
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Найти заявку по имени");
    }
}