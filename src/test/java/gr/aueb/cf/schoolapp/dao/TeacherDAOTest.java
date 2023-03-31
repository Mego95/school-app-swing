package gr.aueb.cf.schoolapp.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.aueb.cf.schoolapp.dao.dbutil.DBHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;

class TeacherDAOTest {
	
	private static ITeacherDAO teacherDAO;
	
	@BeforeAll
	public static void setUpClass() throws SQLException {
		teacherDAO = new TeacherDAOImpl();
		DBHelper.eraseData();
	}
	
	@BeforeEach
	void setUp() throws TeacherDAOException {
		createDummyTeachers();
	}
	
	@AfterEach
	void tearDown() throws SQLException {
		DBHelper.eraseData();
	}

	@Test
	void persistAndGetTeacher() throws TeacherDAOException {
		Teacher teacher = new Teacher();
		teacher.setFirstname("bob");
		teacher.setLastname("dylan");
		teacherDAO.insert(teacher);
		
		List<Teacher> teachers = teacherDAO.getByLastname("dylan");
		assertEquals(1, teachers.size());
		//assertTrue(teachers.size() == 1);
	}

	@Test
	void testUpdate() throws TeacherDAOException {
		Teacher teacher = new Teacher();
		teacher.setId(2);
		teacher.setFirstname("anna2");
		teacher.setLastname("gian2");
		teacherDAO.update(teacher);
		
		List<Teacher> teachers = teacherDAO.getByLastname(teacher.getLastname());
		assertEquals(teachers.get(0).getLastname(), "gian2");
	}

	@Test
	void testDelete() throws TeacherDAOException {
		int id = 1;
		teacherDAO.delete(id);
		
		Teacher teacher = teacherDAO.getById(1);
		assertNull(teacher);
	}

	@Test
	void testGetByLastname() throws TeacherDAOException {
		List<Teacher> teachers = teacherDAO.getByLastname("andr");
		assertEquals(teachers.size(), 1);
	}

	@Test
	void testGetById() throws TeacherDAOException {
		int id = 4;
		Teacher teacher = teacherDAO.getById(id);
		
		assertEquals("lennon", teacher.getLastname());
	}
	
	public static void createDummyTeachers() throws TeacherDAOException {
		Teacher teacher = new Teacher();
		teacher.setFirstname("thanasis");
		teacher.setLastname("androutsos");
		teacherDAO.insert(teacher);
		
		teacher = new Teacher();
		teacher.setFirstname("anna");
		teacher.setLastname("gian");
		teacherDAO.insert(teacher);
		
		teacher = new Teacher();
		teacher.setFirstname("alice");
		teacher.setLastname("w.");
		teacherDAO.insert(teacher);
		
		teacher = new Teacher();
		teacher.setFirstname("john");
		teacher.setLastname("lennon");
		teacherDAO.insert(teacher);
	}

}
