package ru.job4j.control;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StockMarketTest {
    StockMarket market;
    Bid.Builder builder;

    @Before
    public void setMarket() {
        market = new StockMarket();
        builder = new Bid.Builder();
    }

    @Test
    public void whenAddBidThenTrue() {
        Bid bid = builder.setBook(1).setType(1).setAction(1).setPrice(10).setVolume(2).build();
        assertThat(market.addBid(bid), is(true));
        assertThat(market.getGlassfulByBook(1).containsBid(bid.getId()), is(true));
    }

    @Test
    public void whenDelBidThenTrue() {
        Bid bid = builder.setBook(1).setType(1).setAction(1).setPrice(10).setVolume(2).build();
        market.addBid(bid);
        assertThat(market.delBid(bid), is(true));
    }
}
