package gr.aueb.cf.schoolapp.dao;

import java.util.List;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;

public interface ITeacherDAO {
	Teacher insert(Teacher teacher) throws TeacherDAOException;
	Teacher update(Teacher teacher) throws TeacherDAOException;
	void delete(int id) throws TeacherDAOException;
	List<Teacher> getByLastname(String lastname) throws TeacherDAOException;
	Teacher getById(int id) throws TeacherDAOException;
}
