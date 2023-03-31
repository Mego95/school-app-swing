package gr.aueb.cf.schoolapp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.dbutil.DBHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

class TeacherServiceImplTest {

	private static ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private static ITeacherService teacherService;
	
	@BeforeAll
	public static void setupClass() throws SQLException {
		teacherService = new TeacherServiceImpl(teacherDAO);
		DBHelper.eraseData();
	}
	
	@BeforeEach
	public void setup() throws TeacherDAOException {
		createDummyTeachers();
	}
	
	@BeforeEach
	public void tearDown() throws SQLException {
		DBHelper.eraseData();
	}
	
	@Test
	public void insertAndGetTeacher() throws TeacherDAOException {
		TeacherDTO teacherDTO = new TeacherDTO(1, "anna", "gian");
		teacherService.insertTeacher(teacherDTO);
		
		List<Teacher> teachers = teacherService.getTeachersByLastName(teacherDTO.getLastname());
		
		assertEquals(1, teachers.size());
		assertEquals(teachers.get(0).getLastname(), teacherDTO.getLastname());
		assertEquals(teachers.get(0).getFirstname(), teacherDTO.getFirstname());
		
	}
	
	@Test
	public void updateTeacherTest() throws TeacherDAOException, TeacherNotFoundException {
		TeacherDTO teacherDTO = new TeacherDTO(1, "thanasis1", "androutsos1");
		teacherService.updateTeacher(teacherDTO);
		
		List<Teacher> teachers = teacherService.getTeachersByLastName(teacherDTO.getLastname());
		assertEquals(teachers.get(0).getLastname(), teacherDTO.getLastname());
		assertEquals(teachers.get(0).getFirstname(), teacherDTO.getFirstname());
	}
	
	@Test
	public void updateInvalidTeacher() throws TeacherDAOException, TeacherNotFoundException {
		TeacherDTO teacherDTO = new TeacherDTO(10, "thanasis1", "androutsos1");
		
		assertThrows(TeacherNotFoundException.class, () -> {
			teacherService.updateTeacher(teacherDTO);
		});
	}
	
	@Test
	public void deleteTeacherTest() throws TeacherDAOException, TeacherNotFoundException {
		TeacherDTO teacherDTO = new TeacherDTO(1, "thanasis", "androutsos");
		teacherService.deleteTeacher(teacherDTO.getId());
		
		List<Teacher> teachers = teacherService.getTeachersByLastName(teacherDTO.getLastname());
		assertEquals(0, teachers.size());
	}
	
	@Test
	public void deleteInvalidTeacher() throws TeacherDAOException, TeacherNotFoundException {
		TeacherDTO teacherDTO = new TeacherDTO(10, "thanasis", "androutsos");
		
		assertThrows(TeacherNotFoundException.class, () -> {
			teacherService.deleteTeacher(teacherDTO.getId());
		});
	}
	
	public static void createDummyTeachers() throws TeacherDAOException {
		Teacher teacher = new Teacher();
		teacher.setFirstname("thanasis");
		teacher.setLastname("androutsos");
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
