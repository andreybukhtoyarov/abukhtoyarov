package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ProfessionTest {
    @Test
	public void whenProfessionGetFieldsThenFields() {
		Profession profession = new Profession("Engineer", "Moscow State Industrial University", 1000);
		
		StringBuilder exp = new StringBuilder();
		StringBuilder res = new StringBuilder();
		
		String expected = exp.append("Engineer")
							.append("Moscow State Industrial University")
							.append("1000")
							.toString();
		String result = res.append(profession.getProfessionName())
							.append(profession.getProfessionDiplom())
							.append(profession.getSalary())
							.toString();
		
		assertThat(
					result,
					is(expected)
		);
	}
}
