package gr.aueb.cf.schoolapp.model;

public class User {
	private int id;
	private String username;
	private String hashedPassword;
	
	public User() {}
	
	public User(int id, String username, String hashedPassword) {
		this.id = id;
		this.username = username;
		this.hashedPassword = hashedPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", hashedPassword=" + hashedPassword + "]";
	}

}
