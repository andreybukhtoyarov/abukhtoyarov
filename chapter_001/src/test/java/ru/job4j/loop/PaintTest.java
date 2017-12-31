package ru.job4j.loop;

import org.junit.Test;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class PaintTest {
	@Test
	public void whenPyramidHeightFive() {
		Paint paint = new Paint();
		String result = paint.pyramid(5);
		System.out.println(result);
		assertThat(result,
				is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
					.add("    ^    ")
					.add("   ^^^   ")
					.add("  ^^^^^  ")
					.add(" ^^^^^^^ ")
					.add("^^^^^^^^^")
					.toString()
				)
		
		);
	}
	
	@Test
	public void whenPyramidHeightFour() {
		Paint paint = new Paint();
		String result = paint.pyramid(4);
		System.out.println(result);
		assertThat(result,
				is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
					.add("   ^   ")
					.add("  ^^^  ")
					.add(" ^^^^^ ")
					.add("^^^^^^^")
					.toString()
				)
		
		);
	}
	
	@Test
	public void whenRightTrlHeightFour() {
		Paint paint = new Paint();
		String result = paint.rightTrl(4);
		System.out.println(result);
		String ln = System.lineSeparator();
		assertThat(result,
				is(String.format("^   %s^^  %s^^^ %s^^^^%s", ln, ln, ln, ln))
		);
	}
}
