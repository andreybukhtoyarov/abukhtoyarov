package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class PeopleTest {
    @Test
	public void whenPeoleGetFieldsThenFields() {
		People people = new People("Andrey", "Russia, Moscow", 28,
			new Engineer("Moscow State Industrial University", 1000)
		);
		people.setSick(true);
		people.setTrained(true);
		
		StringBuilder exp = new StringBuilder();
		StringBuilder res = new StringBuilder();
		
		String expected = exp.append("Andrey")
							.append("Russia, Moscow")
							.append("28")
							.append("Engineer")
							.append("true")
							.append("true")
							.toString();
		String result = res.append(people.getName())
							.append(people.getAddress())
							.append(people.getAge())
							.append(people.getProfession().getProfessionName())
							.append(people.getIsSick())
							.append(people.getIsTrained())
							.toString();
		
		assertThat(
					result,
					is(expected)
		);
	}
}
