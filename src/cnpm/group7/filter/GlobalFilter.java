package cnpm.group7.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class GlobalFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		Connection cnn = (Connection) request.getAttribute("connection");
		if (cnn == null) {
			DataSource ds = (DataSource) request.getServletContext().getAttribute("datasource");
			try {
				cnn = ds.getConnection();
				request.setAttribute("connection", cnn);
				System.out.println("set connection: "+request.getContextPath());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
