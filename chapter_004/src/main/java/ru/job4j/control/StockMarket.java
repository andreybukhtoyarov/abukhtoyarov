package ru.job4j.control;

import java.util.ArrayList;
import java.util.Map;

/**
 * Класс описывает список со стаканами и методы для работы со ставками.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StockMarket {
    /**
     * Список со всеми стаканами.
     */
    private ArrayList<Glassful> glassfuls = new ArrayList<>();

    /**
     * Добавляет стакан в список.
     * @param gls стакан.
     */
    private void addGlassful(Glassful gls) {
        glassfuls.add(gls);
    }

    /**
     * Добавляет ставку в соответствующий ей стакан.
     * @param bid ставка.
     * @return true если ставка добавлена.
     */
    public boolean addBid(Bid bid) {
        boolean added = false;
        if (bid != null) {
            int book = bid.getBook();
            boolean detected = false;
            if (!glassfuls.isEmpty()) {
                for (Glassful gls : glassfuls) {
                    if (gls.getBook() == book) {
                        gls.addBid(bid);
                        detected = true;
                        added = true;
                        break;
                    }
                }
            }
            if (!detected) {
                Glassful gls = new Glassful(book);
                gls.addBid(bid);
                addGlassful(gls);
                added = true;
            }
        }
        return added;
    }

    /**
     * Удаляет ставку из соответсвующего ей стакана.
     * @param bid ставка.
     * @return true если ставка удалена.
     */
    public boolean delBid(Bid bid) {
        boolean deleted = false;
        if (bid != null) {
            int book = bid.getBook();
            for (Glassful gls : glassfuls) {
                if (gls.getBook() == book) {
                    deleted = gls.delBid(bid);
                    break;
                }
            }
        }
        return deleted;
    }

    /**
     * Возвращеат стакан по его номеру.
     * @param book номер стакана.
     * @return Возвращеат стакан по его номеру.
     */
    public Glassful getGlassfulByBook(int book) {
        Glassful glassful = new Glassful(-1);
        for (Glassful gls : glassfuls) {
            if (gls.getBook() == book) {
                glassful = gls;
                break;
            }
        }
        return glassful;
    }

    /**
     * Выводит на экран сумму всех ставок по цене, для данного эмитента.
     * @param book номер эмитента
     * @param action действие ставки (на покупку/на продажу).
     */
    public void show(int book, int action) {
        Glassful gls = getGlassfulByBook(book);
        Map map = gls.summator(action);
        if (!map.isEmpty()) {
            System.out.println(String.format("%s  %s", "Цена", "Колличество"));
            map.forEach((k, v) -> System.out.println(String.format("%s %s", k, v)));
        } else {
            System.out.println("Нет ставок у данного эмитента.");
        }
    }

}
