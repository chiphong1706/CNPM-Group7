package cnpm.group7.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cnpm.group7.connection.DBConnection;
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
	
	public Account getGoogleAccount(String id_gg) throws SQLException {
		Account result = null;
		String sql = "SELECT * FROM account where id_gg = ?";
		PreparedStatement stmt = cnn.prepareStatement(sql);
		stmt.setString(1, id_gg);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			result = new Account();
			result.setId(rs.getInt("id"));
			result.setEmail(rs.getString("email"));
			result.setId_google(rs.getString("id_gg"));
		}
		return result;
	}
	
	public boolean checkEmail(String email) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = cnn.prepareStatement("SELECT * FROM Account WHERE email = ?");
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) 
			return true;
		return false;
	}
	
	public String getPassword(String email) throws SQLException {
		PreparedStatement ps = cnn.prepareStatement("SELECT pass FROM Account WHERE email = ?");
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
}
