package ru.job4j.control;

import java.util.*;

/**
 * Класс описывает стакан со ставками.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Glassful {
    /**
     * УНикальный номер стакана.
     */
    private final int book;
    /**
     * Сортировка по уменьшению цены.
     */
    private Comparator<Bid> reducePrices = new Comparator<Bid>() {
        @Override
        public int compare(Bid o1, Bid o2) {
            return o2.getPrice() == o1.getPrice()
                    ? Integer.compare(o2.getId(), o1.getId()) : Integer.compare(o2.getPrice(), o1.getPrice());
        }
    };
    /**
     * Множество ставок данного стакана на покупку.
     * Действие на покупку в системе - 1.
     */
    private final TreeSet<Bid> bidSet = new TreeSet<>(reducePrices);
    /**
     * Множество ставок данного стакана на продажу.
     * Действие на продажу в системе - 0.
     */
    private final TreeSet<Bid> askSet = new TreeSet<>(reducePrices);

    public Glassful(int book) {
        this.book = book;
    }

    /**
     * Возвращает номер стакана.
     * @return номер стакана.
     */
    public int getBook() {
        return book;
    }

    private boolean execute(Bid one, TreeSet<Bid> set) {
        boolean b = true;
        Bid two = set.first();
        int oneVol = one.getVolume();
        int twoVol = two.getVolume();
        one.setVolume(oneVol - twoVol);
        two.setVolume(twoVol - oneVol);
        if (two.getVolume() <= 0) {
            set.remove(two);
        }
        if (one.getVolume() <= 0) {
            b = false;
        }
        return b;
    }

    /**
     * Метод добавляет ставку в стакан.
     * @param bid новая ставка.
     */
    public void addBid(Bid bid) {
        if (bid != null) {
            boolean b = true;
            if (bid.getAction() == 1) {
                if (!askSet.isEmpty() && bid.getPrice() >= askSet.first().getPrice()) {
                    do {
                        b = execute(bid, askSet);
                    } while (b && !askSet.isEmpty() && bid.getPrice() >= askSet.first().getPrice());
                }
                if (b) {
                    bidSet.add(bid);
                }
            } else if (bid.getAction() == 0) {
                if (!bidSet.isEmpty() && bid.getPrice() <= bidSet.first().getPrice()) {
                    do {
                        b = execute(bid, bidSet);
                    } while (b && !bidSet.isEmpty() && bid.getPrice() <= bidSet.first().getPrice());
                }
                if (b) {
                    askSet.add(bid);
                }
            }
        }
    }

    /**
     * Метод удаляет ставку из стакана.
     * @param bid ставка для удаления.
     * @return true если ставка удалена.
     */
    public boolean delBid(Bid bid) {
        boolean deleted = false;
        if (bid != null) {
            if (bid.getVolume() > 0) {
                if (bid.getAction() == 1 && !bidSet.isEmpty()) {
                    deleted = delBidInSet(bidSet, bid);
                } else if (bid.getAction() == 0 && !bidSet.isEmpty()) {
                    deleted = delBidInSet(askSet, bid);
                }
            }
        }
        return deleted;
    }

    /**
     * Удаляет ставку непосредственно из множества ставок.
     * @param set нужное множество стаквок.
     * @param bid заявка на удаление.
     * @return true если ставка удалена.
     */
    private boolean delBidInSet(TreeSet<Bid> set, Bid bid) {
        boolean deleted = false;
        Iterator<Bid> iter = set.iterator();
        while (iter.hasNext()) {
            Bid now = iter.next();
            if (now.getPrice() == bid.getPrice()) {
                int nowVolume = now.getVolume();
                if (now.setVolume(nowVolume - bid.getVolume()) <= 0) {
                    bid.setVolume(bid.getVolume() - nowVolume);
                    iter.remove();
                    deleted = true;
                }
            }
        }
        return deleted;
    }

    /**
     * Выясняет содержится ли ставка во множестве ставок.
     * @param id id ставки.
     * @return true если ставка сожержится во множестве.
     */
    public boolean containsBid(int id) {
        boolean contains = false;
        for (Bid bid : askSet) {
            if (bid.getId() == id) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            for (Bid bid : bidSet) {
                if (bid.getId() == id) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }

    /**
     * Возвращает Optional<Bid>, первую по номеру, по действию.
     * @param action действие заявки.
     * @return Optional<Bid>.
     */
    public Optional<Bid> getFirst(int action) {
        Optional<Bid> optBid = Optional.empty();
        if (action == 0) {
            optBid = Optional.of(askSet.first());
        } else if (action == 1) {
            optBid = Optional.of(bidSet.first());
        }
        return optBid;
    }

    /**
     * Возвращает Optional<Bid> по id и множеству.
     * @param id id заявки.
     * @param set множество заявок.
     * @return Optional<Bid>.
     */
    private Optional<Bid> getBid(int id, TreeSet<Bid> set) {
        Optional<Bid> optBid = Optional.empty();
        if (set != null) {
            for (Bid bid : set) {
                if (bid.getId() == id) {
                    optBid = Optional.of(bid);
                    break;
                }
            }
        }
        return optBid;
    }

    public Optional<Bid> getBidByIdAndAction(int id, int action) {
        Optional<Bid> optBid;
        if (action == 1) {
            optBid = getBid(id, bidSet);
        } else {
            optBid = getBid(id, askSet);
        }
        return optBid;
    }

    /**
     * Возвращает словарь ставок по действию вида: цена - количество.
     * @param action действие ставки.
     * @return словарь ставок по действию вида: цена - количество.
     */
    Map<Integer, Integer> summator(int action) {
        Iterator<Bid> iter;
        Map<Integer, Integer> map = new HashMap<>();
        if (action == 0) {
            iter = askSet.iterator();
        } else {
            iter = bidSet.iterator();
        }
        while (iter.hasNext()) {
            if (map.isEmpty()) {
                Bid bid = iter.next();
                map.put(bid.getPrice(), bid.getVolume());
            } else {
                Bid bid = iter.next();
                if (map.containsKey(bid.getPrice())) {
                    map.put(bid.getPrice(), map.get(bid.getPrice()) + bid.getVolume());
                } else {
                    map.put(bid.getPrice(), bid.getVolume());
                }
            }
        }
        return map;
    }
}
