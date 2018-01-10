package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class TeacherTest {
    @Test
	public void whenTeacherGetFieldsThenFields() {
		Teacher teacher = new Teacher("GUU", 300);
		
		StringBuilder exp = new StringBuilder();
		StringBuilder res = new StringBuilder();
		
		String expected = exp.append("Teacher")
							.append("GUU")
							.append("300")
							.toString();
		String result = res.append(teacher.getProfessionName())
							.append(teacher.getProfessionDiplom())
							.append(teacher.getSalary())
							.toString();
		
		assertThat(
					result,
					is(expected)
		);
	}
	
	@Test
	public void whenTeachPeopleThenIsTrainedEqualsTrue() {
		Teacher teacher = new Teacher("GUU", 300);
		People student = new People("Student", "Russia", 18);
		
		teacher.teach(student);
		boolean result = student.getIsTrained();
		boolean expected = true;
		
		assertThat(
					result,
					is(expected)
		);
	}
	
	@Test
	public void whenTeachPeopleThenTakeExamEqualsFive() {
		Teacher teacher = new Teacher("GUU", 300);
		People student = new People("Student", "Russia", 18);
		
		teacher.teach(student);
		
		int result = teacher.takeExam(student);
		int expected = 5;
		
		assertThat(
					result,
					is(expected)
		);
	}
	
	@Test
	public void whenNotTeachPeopleThenTakeExamEqualsTwo() {
		Teacher teacher = new Teacher("GUU", 300);
		People student = new People("Student", "Russia", 18);
		
		int result = teacher.takeExam(student);
		int expected = 2;
		
		assertThat(
					result,
					is(expected)
		);
	}
}
