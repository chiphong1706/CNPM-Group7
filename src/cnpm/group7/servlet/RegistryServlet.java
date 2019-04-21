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
 * Servlet implementation class RegistryServlet
 */
@WebServlet("/registry")
public class RegistryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Connection cnn = (Connection) request.getAttribute("connection");
		AccountDAO accountDAO = new AccountDAO(cnn);
		try {
			if (!accountDAO.isExistedEmail(email)) {
				Account account = new Account();
				account.setEmail(email);
				account.setPassword(password);
				accountDAO.insertAccount(account);
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				response.sendRedirect("index");
			} else {
				request.setAttribute("error_email", "Email is existed!");
				doGet(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
