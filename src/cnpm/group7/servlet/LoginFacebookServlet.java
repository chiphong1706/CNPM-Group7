package cnpm.group7.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.restfb.types.User;

import cnpm.group7.DAO.AccountDAO;
import cnpm.group7.accessfacebook.common.RestFB;
import cnpm.group7.model.Account;

/**
 * Servlet implementation class LoginFacebookServlet
 */
@WebServlet("/login-facebook")
public class LoginFacebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFacebookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String code = request.getParameter("code");
		
		if (code == null || code.isEmpty()) {
			System.out.println("Empty Facebook code");
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		} else {
			String accessToken = RestFB.getToken(code);
			User user = RestFB.getUserInfo(accessToken);
			request.setAttribute("id", user.getId());
			request.setAttribute("name", user.getName());
			request.setAttribute("email", user.getEmail());
			
			java.sql.Connection conn = (java.sql.Connection) request.getAttribute("connection");
			
			AccountDAO aDAO = new AccountDAO(conn);
			
			try {
				Account account = aDAO.getFacebookAccount(user.getId());
				if (account == null) {
					account = new Account();
					account.setEmail(user.getEmail());
					account.setId_facebook(user.getId());
					aDAO.insertAccount(account);
				}
				System.out.println(account.getId()+"\t"+account.getEmail()+ "\t"+account.getId_facebook());
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
