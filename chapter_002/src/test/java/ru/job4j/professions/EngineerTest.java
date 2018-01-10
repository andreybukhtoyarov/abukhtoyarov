package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class EngineerTest {
    @Test
	public void whenDevelopProjectGetIsWellDoneThenTrue() {
		Engineer engineer = new Engineer("Moscow State Industrial University", 1000);
		Project project = new Project();
		
		engineer.develop(project);
		
		boolean result = project.isWellDone();
		boolean expected = true;
		
		assertThat(
					result,
					is(expected)
		);
	}
	
	@Test
	public void whenNotDevelopProjectGetIsWellDoneThenFalse() {
		Engineer engineer = new Engineer("Moscow State Industrial University", 1000);
		Project project = new Project();
		
		boolean result = project.isWellDone();
		boolean expected = false;
		
		assertThat(
					result,
					is(expected)
		);
	}
}
