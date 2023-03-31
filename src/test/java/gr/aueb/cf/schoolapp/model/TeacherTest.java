package gr.aueb.cf.schoolapp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TeacherTest {

	@Test
	void gettersSettersTest() {
		Teacher teacher = new Teacher();
		teacher.setId(1);
		assertEquals(teacher.getId(), 1);
		
		teacher.setFirstname("bob");
		assertEquals(teacher.getFirstname(), "bob");
		
		teacher.setLastname("dylan");
		assertEquals(teacher.getLastname(), "dylan");
	}
	
	@Test
	void overloadedConstructorAndToString() {
		Teacher teacher = new Teacher(2, "testName", "testLastname");
		
		assertEquals(teacher.toString(),
				"Teacher [id=" + teacher.getId() + ", firstname=" + teacher.getFirstname() + ", lastname=" + teacher.getLastname() + "]");
	}

}
