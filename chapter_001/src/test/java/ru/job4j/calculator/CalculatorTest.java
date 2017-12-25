package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@since 25.12.2017.
 */
public class CalculatorTest {
    @Test
    public void whenSubtractTwheeSubtractTwoThenOne() {
        Calculator calc = new Calculator();
        calc.subtract(3D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivSixDivisionTwoThenThree() {
        Calculator calc = new Calculator();
        calc.div(6D, 2D);
        double result = calc.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultipleTwoMultipleThreeThanSix() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 3D);
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddTwoPlusSevenThanNine() {
        Calculator calc = new Calculator();
        calc.add(2D, 7D);
        double result = calc.getResult();
        double expected = 9D;
        assertThat(result, is(expected));
    }
}