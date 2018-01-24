package ru.job4j.trackerrefactor;

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
    protected StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * This method is main program cycle.
     */
    protected void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.showMenu();
            int key = this.input.ask("Введите пункт меню : ", menu.getRange());
            menu.selectAction(key);
        } while (!"y".equals(this.input.ask("Выйти? y или n : ")));

    }

    /**
     * Start application.
     * @param args - arguments.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}
