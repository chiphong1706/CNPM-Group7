package cnpm.group7.connection;

import java.sql.*;

public class DBConnection {
	
	public static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:sqlserver://localhost:1456;databaseName=Login"; ////Phải kết nối đúng cổng (có thể là cổng IPALL)
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url ,"sa", "chiphong1706");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		if (con != null) {
			System.out.println("Kết nối thành công");
		} else {
			System.out.println("Kết nối thất bại");
		}
	}
}
