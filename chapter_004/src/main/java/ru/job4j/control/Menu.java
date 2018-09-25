package ru.job4j.control;

import static java.lang.Integer.parseInt;

public class Menu {
    /**Field with StockMarket.*/
    private final StockMarket sm;
    /**Field with system input.*/
    private final Input input;
    /**Field with array of user actions.*/
    private UserAction[] actions = new UserAction[2];
    /**Field with array of range menu.*/
    private int[] range = {0, 1};

    public Menu(StockMarket sm, Input input) {
        this.sm = sm;
        this.input = input;
    }

    public int[] getRange() {
        return this.range;
    }

    public void selectAction(int key) {
        this.actions[key].execute(this.input, this.sm);
    }

    /**
     * This method filled actions array.
     */
    public void fillActions() {
        this.actions[0] = new AddBid(0, "Добавить новую заявку");
        this.actions[1] = new ShowBids(1, "Показать обще число ставок");
    }

    /**
     * Выводит на экран описание UserActions.
     */
    public void show() {
        for (UserAction act : actions) {
            System.out.println(act.info());
        }
    }

    /**
     * Класс расширяет UserAction. И служит для добавления ставки.
     */
    public class AddBid extends UserAction {

        public AddBid(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute(Input input, StockMarket sm) {
            System.out.println("Добавление новой заявки");
            Bid.Builder builder = new Bid.Builder();
            int type = Integer.parseInt(input.ask("Введите тип заявки: 1 - добавить заявку, 0 - снять заявку:"));
            Bid bid;
            builder.setBook(parseInt(input.ask("Введите имитента:")))
                    .setAction(parseInt(input.ask("Введите действие заявки: 1 - заявка на покупку, 0 - заявка на продажу:")))
                    .setPrice(parseInt(input.ask("введите цену:")))
                    .setVolume(parseInt(input.ask("Введите количество:")));
            if (type == 1) {
                bid = builder.setType(type).build();
                sm.addBid(bid);
            } else if (type == 0) {
                bid = builder.setType(type).build();
                sm.delBid(bid);
            }
        }
    }

    /**
     * Класс расширяет UserAction. И служит для вывода на экран всех ставок.
     */
    public class ShowBids extends UserAction {

        public ShowBids(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute(Input input, StockMarket sm) {
            System.out.println("Показать ставки");
            int book = Integer.parseInt(input.ask("Введите бук"));
            int action = Integer.parseInt(input.ask("Введите действие заявки"));
            sm.show(book, action);
        }
    }
}
