package model.dao;

import java.sql.*;

import connection.DBConnection;

public class AccountDAO {
	
	//7.	Cơ sở dữ liệu kiểm tra email
	public static boolean checkEmail(String email) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM Account WHERE email = ?");
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) return true;
		return false;
	}

	//10.	Cơ sở dữ liệu trả về mật khẩu
	public static String getPassword(String email) throws SQLException {
		Connection con = DBConnection.getConnection();
		
		PreparedStatement ps = con.prepareStatement("SELECT pass FROM Account WHERE email = ?");
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
}
