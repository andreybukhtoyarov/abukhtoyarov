package ru.job4j.control;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineSurrenderTest {

    @Test
    public void whenValueAndPriceThen() {
        CoffeeMachineSurrender machine = new CoffeeMachineSurrender();
        assertThat(machine.changes(50, 35), is(new int[] {10, 5}));
        assertThat(machine.changes(60, 35), is(new int[] {10, 10, 5}));
        assertThat(machine.changes(36, 35), is(new int[] {1}));
        assertThat(machine.changes(37, 35), is(new int[] {2}));
        assertThat(machine.changes(38, 35), is(new int[] {2, 1}));
    }

    @Test
    public void whenValueAndPriceAreEqualThenNull() {
        CoffeeMachineSurrender machine = new CoffeeMachineSurrender();
        assertThat(machine.changes(35, 35), is(new IsNull<>()));

    }

    @Test
    public void whenValueLessThanPriceThenNull() {
        CoffeeMachineSurrender machine = new CoffeeMachineSurrender();
        assertThat(machine.changes(30, 35), is(new IsNull<>()));

    }
}
