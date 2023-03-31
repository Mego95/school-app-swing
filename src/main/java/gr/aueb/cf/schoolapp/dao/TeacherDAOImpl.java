package gr.aueb.cf.schoolapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

public class TeacherDAOImpl implements ITeacherDAO {

	@Override
	public Teacher insert(Teacher teacher) throws TeacherDAOException {
		String sql = "INSERT INTO TEACHERS (LASTNAME, FIRSTNAME) VALUES (?, ?)";
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement p = conn.prepareCall(sql)) {
			
			String firstname = teacher.getFirstname();
			String lastname = teacher.getLastname();
			
			if (firstname.equals("") || lastname.equals("")) {
				return null;
			}
			
			p.setString(1, lastname);
			p.setString(2, firstname);
			p.executeUpdate();
			return teacher;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TeacherDAOException("SQL Error in Teacher " + teacher + " insertion");
		}
	}

	@Override
	public Teacher update(Teacher teacher) throws TeacherDAOException {
	String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
			
			try (Connection conn = DBUtil.getConnection();
					PreparedStatement p = conn.prepareCall(sql)) {
				
				String firstname = teacher.getFirstname();
				String lastname = teacher.getLastname();
				int id = teacher.getId();
				
				if (firstname.equals("") || lastname.equals("") || String.valueOf(id).equals("")) {
					return null;
				}
				
				p.setString(1, firstname);
				p.setString(2, lastname);
				p.setInt(3, id);
				p.executeUpdate();
				return teacher;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new TeacherDAOException("SQL Error in Teacher " + teacher + " update");
			}
	}

	@Override
	public void delete(int id) throws TeacherDAOException {
		String sql = "DELETE FROM TEACHERS WHERE ID = ?";
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement p = conn.prepareStatement(sql)) {
			
//			if (id <= 0) {
//				return;
//			}
			
			p.setInt(1, id);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TeacherDAOException("SQL Error in deleting " + id + " id");
		}
		
	}

	@Override
	public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
		String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE LASTNAME LIKE ?";
		ResultSet rs;
		List<Teacher> teachers = new ArrayList<>();
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement p = conn.prepareStatement(sql)) {
			
			p.setString(1, lastname + "%");
			rs = p.executeQuery();
			
			while (rs.next()) {
				Teacher teacher = new Teacher(
						rs.getInt("ID"),
						rs.getString("FIRSTNAME"),
						rs.getString("LASTNAME"));
				teachers.add(teacher);
			}
			
			return teachers;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TeacherDAOException("Error in Teacher with lastname = " + lastname);
		}
	}

	@Override
	public Teacher getById(int id) throws TeacherDAOException {
		String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE ID = ?";
		Teacher teacher = null;
		ResultSet rs;
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement p = conn.prepareStatement(sql)) {
			
			p.setInt(1, id);
			rs = p.executeQuery();
			
			if (rs.next()) {
				teacher = new Teacher(
						rs.getInt("ID"),
						rs.getString("FIRSTNAME"),
						rs.getString("LASTNAME"));
			}
			
			return teacher;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TeacherDAOException("SQL Error in getting Teacher with id: " + id);
		}
	}

}
