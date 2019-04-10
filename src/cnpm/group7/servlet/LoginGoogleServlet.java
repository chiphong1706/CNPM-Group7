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
import cnpm.group7.accessgoogle.common.GooglePojo;
import cnpm.group7.accessgoogle.common.GoogleUtils;
import cnpm.group7.model.Account;

/**
 * Servlet implementation class LoginGoogleServlet
 */
@WebServlet("/login-google")
public class LoginGoogleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginGoogleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) 
			System.out.println("empty code google");
		else {
			String accessToken = GoogleUtils.getToken(code);
			GooglePojo ggPojo = GoogleUtils.getUserInfo(accessToken);
			
			Connection cnn = (Connection) request.getAttribute("connection");
			AccountDAO accountDAO = new AccountDAO(cnn);
			
			try {
				// check if account null, then create new account from GooglePojo's info
				Account account = accountDAO.getGoogleAccount(ggPojo.getId());
				if (account == null) {
					account = new Account();
					account.setEmail(ggPojo.getEmail());
					account.setId_google(ggPojo.getId());
					accountDAO.insertAccount(account); // save new account into DB
				}
				System.out.println(account.getEmail() +" "+account.getId_google());
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		response.sendRedirect("index");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}
}
