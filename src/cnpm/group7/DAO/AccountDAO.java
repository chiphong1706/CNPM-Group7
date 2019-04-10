package cnpm.group7.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cnpm.group7.model.Account;

public class AccountDAO {
	private Connection cnn;

	public AccountDAO(Connection cnn) {
		super();
		this.cnn = cnn;
	}
	
	public boolean insertAccount(Account a) throws SQLException {
		String sql = "INSERT INTO account(email, password, id_fb, id_gg) VALUES(?, ?, ?, ?)";
		PreparedStatement stmt = cnn.prepareStatement(sql);
		stmt.setString(1, a.getEmail());
		stmt.setString(2, a.getPassword());
		stmt.setString(3, a.getId_facebook());
		stmt.setString(4, a.getId_google());
		boolean result = stmt.executeUpdate() > 0 ? true : false;
		stmt.close();
		
		return result;
	}
}
