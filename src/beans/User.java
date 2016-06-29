package beans;

public class User {

	private String password = "";
	private String name = "";
	private String userName = "";
	private String filiala = "";
	private String message = "mes";

	public User() {

	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFiliala() {
		return filiala;
	}

	public void setFiliala(String filiala) {
		this.filiala = filiala;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public boolean validate() {

		if (name == null) {
			message = "Invalid name";
			return false;
		}

		if (password == null) {
			message = "Invalid password";
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "User [ name=" + name + ", userName=" + userName + ", filiala=" + filiala + ", message=" + message + "]";
	}

}
