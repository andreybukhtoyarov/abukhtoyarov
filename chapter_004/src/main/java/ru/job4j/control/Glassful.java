package ru.job4j.control;

import java.util.*;

public class Glassful {

    private final int book;

    private Comparator<Bid> reducePrices = new Comparator<Bid>() {
        @Override
        public int compare(Bid o1, Bid o2) {
            return Integer.compare(o2.getPrice(), o1.getPrice());
        }
    };

    private final TreeSet<Bid> bidSet = new TreeSet<>(reducePrices); //покупаем - 1
    private final TreeSet<Bid> askSet = new TreeSet<>(reducePrices); //продаем - 0

    public Glassful(int book) {
        this.book = book;
    }

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

    public void addBid(Bid bid) {
        if (bid != null) {
            boolean b = true;
            if (bid.getAction() == 1 ) {
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

    public boolean delBid(Bid bid) {
        boolean deleted = false;
        if (bid != null) {
            int volume = bid.getVolume();
            if (volume > 0) {
                if (bid.getAction() == 1 && !bidSet.isEmpty()) {
                    deleted = delBidInSet(bidSet, bid, volume);
                } else if (bid.getAction() == 0 && !bidSet.isEmpty()) {
                    deleted = delBidInSet(askSet, bid, volume);
                }
            }
        }
        return deleted;
    }

    private boolean delBidInSet(TreeSet<Bid> set, Bid bid, int volume) {
        boolean deleted = false;
        for (Bid b : set) {
            if (b.getPrice() == bid.getPrice()) {
                int bVolume = b.getVolume();
                if (b.setVolume(bVolume - volume) <= 0) {
                    bid.setVolume(volume - bVolume);
                    set.remove(b);
                } else {
                    deleted = true;
                    break;
                }
                if (bid.getVolume() > 0) {
                    delBid(bid); // ПРОВЕРИТЬ ЭТО МЕСТО!!!!!!
                } else {
                    deleted = true;
                    break;
                }
            }
        }
        return deleted;
    }

    public boolean containsBid(Bid bid) {
        boolean contains = false;
        if (bid != null) {
            if (bid.getAction() == 1) {
                contains = this.bidSet.contains(bid);
            } else if (bid.getAction() == 0) {
                contains = this.askSet.contains(bid);
            }
        }
        return contains;
    }

    public Optional<Bid> getFirst(int action) {
        Optional<Bid> optBid = Optional.empty();
        if (action == 0) {
            optBid = Optional.of(askSet.first());
        } else if (action == 1) {
            optBid = Optional.of(bidSet.first());
        }
        return optBid;
    }

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

    private class Iter extends GlassfulIterator {

        public Iter(Set<Bid> set) {
            super(set);
        }
    }

    public class AskSetIterator implements Iterable<Bid> {

        @Override
        public Iterator<Bid> iterator() {
            return new Iter(askSet);
        }
    }

    public class BidSetIterator implements Iterable<Bid> {

        @Override
        public Iterator<Bid> iterator() {
            return new Iter(bidSet);
        }
    }

    private Map<Integer, Integer> summator(int action) {
        Iterator<Bid> iter;
        Map<Integer, Integer> map = new HashMap<>();
        if (action == 0) {
            iter = new AskSetIterator().iterator();
        } else {
            iter = new BidSetIterator().iterator();
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
