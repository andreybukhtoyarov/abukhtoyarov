package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class DoctorTest {
    @Test
	public void whenDiagnosticPersonDoctorThenDiagnosis() {
		People personDoctor = new People("Andrey", "Russia, Moscow", 28,
			new Doctor("MGU", 3000));
		Doctor doctor = new Doctor("MGU", 3000);
		
		String result = doctor.diagnostic(personDoctor);
		String expected = "Хроническая усталость";
		
		assertThat(
					result,
					is(expected)
		);
	}
	
	@Test
	public void whenDiagnosticPersonEngineerThenDiagnosis() {
		People personEngineer = new People("Andrey", "Russia, Moscow", 28,
			new Engineer("Moscow State Industrial University", 1000));
		Doctor doctor = new Doctor("MGU", 3000);
		
		String result = doctor.diagnostic(personEngineer);
		String expected = "Хроническая усатость";
		
		assertThat(
					result,
					is(expected)
		);
	}
	
	@Test
	public void whenDiagnosticPersonTeacherThenDiagnosis() {
		People personTeacher = new People("Andrey", "Russia, Moscow", 28,
			new Teacher("GUU", 300));
		Doctor doctor = new Doctor("MGU", 3000);
		
		String result = doctor.diagnostic(personTeacher);
		String expected = "Хроническая крикливость";
		
		assertThat(
					result,
					is(expected)
		);
	}
	
	@Test
	public void whenHealThenIsSickFalse() {
		People personTeacher = new People("Andrey", "Russia, Moscow", 28,
			new Teacher("GUU", 300));
		Doctor doctor = new Doctor("MGU", 3000);
		
		doctor.heal(personTeacher);
		
		boolean result = personTeacher.getIsSick();
		boolean expected = false;
		
		assertThat(
					result,
					is(expected)
		);
	}
}
