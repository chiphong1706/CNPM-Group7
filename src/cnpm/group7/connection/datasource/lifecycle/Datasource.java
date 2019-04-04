package cnpm.group7.connection.datasource.lifecycle;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class Datasource implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// close connection
		Connection cnn = (Connection) e.getServletContext().getAttribute("connection");
		if (cnn != null)
			try {
				cnn.close();
				System.out.println("closed connection");
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/cnpmDB");
			e.getServletContext().setAttribute("datasource", ds);
			System.out.println("set datasource");
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
	}

}
