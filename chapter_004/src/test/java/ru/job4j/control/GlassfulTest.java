package ru.job4j.control;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GlassfulTest {
    Glassful one;
    Glassful two;
    Bid.Builder builder;

    @Before
    public void createGlasses() {
        one = new Glassful(1);
        two = new Glassful(2);
        builder = new Bid.Builder();
        Bid one = builder.setBook(1).setType(1).setAction(1).setPrice(10).setVolume(5).build();
        Bid two = builder.setBook(1).setType(1).setAction(1).setPrice(8).setVolume(4).build();
        this.one.addBid(one);
        this.one.addBid(two);
    }

    @Test
    public void whenOneContainsBidThenTrue() {
        int id = one.getFirst(1).get().getId();
        assertThat(one.containsBid(id), is(true));
        assertThat(one.containsBid(id), is(true));
        assertThat(one.getFirst(1).get().getPrice(), is(10));
        assertThat(one.getFirst(1).get().getVolume(), is(5));
    }

    @Test
    public void whenAddBidToAskThenBidBidVolumeIsFive() {
        Bid bidBid = one.getFirst(1).get();
        Bid bidAsk = builder.setBook(1).setAction(0).setPrice(9).setType(1).setVolume(10).build();
        one.addBid(bidAsk);
        assertThat(one.containsBid(bidBid.getId()), is(false));
        assertThat(one.containsBid(bidAsk.getId()), is(true));
        assertThat(one.getFirst(0).get().getVolume(), is(5));
    }

    @Test
    public void whenDeleteBidThenBidDeleted() {
        one.delBid(one.getFirst(1).get());
        assertThat(one.getFirst(1).get().getPrice(), is(8));
        assertThat(one.getFirst(1).get().getVolume(), is(4));
    }

    @Test
    public void whenDeleteThen() {
        one.addBid(builder.setPrice(8).setVolume(9).build());
        one.delBid(one.getFirst(1).get());
        one.delBid(builder.setBook(1).setType(1).setAction(1).setPrice(8).setVolume(10).build());
        assertThat(one.getFirst(1).get().getVolume(), is(3));
    }

}
