package ru.job4j.control;

public class StartUI {
    /**Field with system input.*/
    private final Input input;
    /**Field with task Tracker.*/
    private final StockMarket market;

    public StartUI(Input input, StockMarket market) {
        this.input = input;
        this.market = market;
    }

    /**
     * Проверка валидности пункта меню.
     * @param menu меню.
     * @return ключ меню.
     */
    private int validateKey(Menu menu) {
        int key = -1;
        boolean valid = false;
        do {
            try {
                key = this.input.ask("Введите пункт меню : ", menu.getRange());
                valid = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
        return key;
    }

    /**
     * This method is main program cycle.
     */
    protected void init() {
        Menu menu = new Menu(market, input);
        menu.fillActions();
        do {
            menu.show();
            int key = validateKey(menu);
            if (key != -1) {
                menu.selectAction(key);
            }
        } while (!"y".equals(this.input.ask("Выйти? y или n : ")));
    }

    /**
     * Start application.
     * @param args - arguments.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new StockMarket()).init();
    }
}
