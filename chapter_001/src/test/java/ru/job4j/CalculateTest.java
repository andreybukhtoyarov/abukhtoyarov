package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
*Test.
*@autor Andrey Bukhtoyarov (andreymedoed@gmail.com)
*@since 20.12.2017
*@version 1.0
*/
public class CalculateTest {

	/**
	*Method echo.
	*/ @Test
	public void whenTakeNameThenTreeEchoPlusName() {
		String input = "Andrey Bukhtoyarov";
		String expect = "Echo, echo, echo : Andrey Bukhtoyarov"; 
		
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}