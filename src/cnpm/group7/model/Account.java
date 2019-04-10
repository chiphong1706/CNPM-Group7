package cnpm.group7.model;

public class Account {
	private int id;
	private String email, password, id_google, id_facebook;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId_google() {
		return id_google;
	}

	public void setId_google(String id_google) {
		this.id_google = id_google;
	}

	public String getId_facebook() {
		return id_facebook;
	}

	public void setId_facebook(String id_facebook) {
		this.id_facebook = id_facebook;
	}

}
