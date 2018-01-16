package ru.job4j.tracker;

/**
 * This class start user interface.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StartUI {
    /**Field with system input.*/
    private final Input input;
    /**Field with task Tracker.*/
    private final Tracker tracker;

    /**
     * Constructor StartUI.
     * @param input - system input.
     * @param tracker - task Tracker.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * This method print the menu.
     */
    private void showMenu() {
        System.out.println("Меню:");
        System.out.println("0. Добавить новую заявку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Редактировать заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти заявку по id");
        System.out.println("5. Найти заявку по имени");
        System.out.println("6. Выйти из программы");
    }

    /**
     * This method is main program cycle.
     */
    private void init() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            switch (answer) {
                case "0":
                    this.createItem();
                    break;
                case "1":
                    showAll();
                    break;
                case "2":
                    editItem();
                    break;
                case "3":
                    deleteItem();
                    break;
                case "4":
                    findItemById();
                    break;
                case "5":
                    findItemByName();
                    break;
                case "6":
                    exit = true;
                    break;
                default:
            }
        }
    }

    /**
     * This method create task and add it to Tracker.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой языки --------------");
        String name = this.input.ask("Введите название заявки :");
        String description = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, description, System.currentTimeMillis());
        this.tracker.add(item);
        System.out.println("------------ Заявка добавлена, id заявки : " + item.getId() + " ------------");
    }

    /**
     * This method show all task in Tracker.
     */
    private void showAll() {
        System.out.println("------------ Все заявки ------------");
        Item[] itemArray = this.tracker.getAll();
        for (Item item : itemArray) {
            System.out.println("id заявки : " + item.getId());
            System.out.println("Название заявки : " + item.getName());
            System.out.println("Описание заявки : " + item.getDescription());
            System.out.println("------------ Конец заявки " + item.getId() + " ------------");
        }
        System.out.println("------------ Все заявки показаны------------");
    }

    /**
     * This method edit task by id.
     */
    private void editItem() {
        System.out.println("------------ Редактируем заявку по id ------------");
        String id = this.input.ask("Введите id заявки :");
        Item itemNew = null;
        Item[] itemsArray = this.tracker.getAll();

        for (int index = 0; index < itemsArray.length; ++index) {
            if (itemsArray[index].getId().equals(id)) {
                String name = this.input.ask("Введите новое имя заявки :");
                String description = this.input.ask("Введите новое описание заявки :");
                itemNew = new Item(name, description, System.currentTimeMillis());
                break;
            } else if (index == itemsArray.length - 1) {
                System.out.println("------------ Вы ввели не существующий id ------------");
                return;
            }
        }
        this.tracker.replace(id, itemNew);
        System.out.println("------------ Заявка успешно отредактирована ------------");
    }

    /**
     * This method delete task by id.
     */
    private void deleteItem() {
        System.out.println("------------ Удаляем заявку по id ------------");
        String id = this.input.ask("Введите id заявки :");
        Item[] itemsArray = this.tracker.getAll();

        for (int index = 0; index < itemsArray.length; ++index) {
            if (!id.equals(itemsArray[index].getId()) && index == itemsArray.length - 1) {
                System.out.println("------------ Вы ввели не существующий id ------------");
                return;
            }
        }
        this.tracker.delete(id);
    }

    /**
     * This method find task by id.
     */
    private void findItemById() {
        System.out.println("------------ Ищем заявку по id ------------");
        String id = this.input.ask("Введите id заявки :");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("id заявки : " + item.getId());
            System.out.println("Название заявки : " + item.getName());
            System.out.println("Описание заявки : " + item.getDescription());
        } else {
            System.out.println("------------ Вы ввели не существующий id ------------");
            return;
        }
    }

    /**
     * This method find task by name.
     */
    private void findItemByName() {
        System.out.println("------------ Ищем заявку по имени ------------");
        String name = this.input.ask("Введите имя заявки :");
        Item[] items = this.tracker.findByName(name);
        if (items != null) {
            for (Item item : items) {
                System.out.println("id заявки : " + item.getId());
                System.out.println("Название заявки : " + item.getName());
                System.out.println("Описание заявки : " + item.getDescription());
                System.out.println("------------ Конец заявки ------------");
            }
        } else {
            System.out.println("------------ Вы ввели не существующее имя ------------");
            return;
        }
    }

    /**
     * Start application.
     * @param args - arguments.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
