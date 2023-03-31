package gr.aueb.cf.schoolapp.service.util;

import org.mindrot.jbcrypt.BCrypt;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

public class UserAuthUtil {
	private static final IUserDAO dao = new UserDAOImpl();
	
	private UserAuthUtil() {}
	
	public static boolean isValidPassword(UserDTO userDTO) throws UserDAOException, UserNotFoundException {
		try {
			User user = dao.getUserByUsername(userDTO.getUsername());
			
			if (user == null) {
				throw new UserNotFoundException("Username " + userDTO.getUsername() + " does not exist");
			}
			
			String inputPassword = userDTO.getPassword();
			String hashedPassword = user.getHashedPassword();
			
			return BCrypt.checkpw(inputPassword, hashedPassword);
		} catch (UserDAOException | UserNotFoundException e) {
			throw e;
		}
	}
	
	public static boolean isAdmin(UserDTO userDTO) {
		String adminPassword = System.getenv("TS_ADMIN_PASSWORD");
		String username = userDTO.getUsername();
		String password = userDTO.getPassword();
		
		return username.equals("admin") && password.equals(adminPassword);
	}
}
