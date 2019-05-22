package cnpm.group7.servlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cnpm.group7.DAO.AccountDAO;
import cnpm.group7.model.Account;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Connection cnn = (Connection) request.getAttribute("connection");

		String url = "";
		Account account = new Account();
		AccountDAO acountDao = new AccountDAO(cnn);

		HttpSession session = request.getSession();

		account = acountDao.check(request.getParameter("email"), request.getParameter("password"));
		try {
			cnn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (account != null) {

			session.setAttribute("account", account);
			url = "index"; // login thanh cong
			response.sendRedirect(url);
		} else {
			request.setAttribute("error_login", "Email or Passord incorrect! ");
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

}
