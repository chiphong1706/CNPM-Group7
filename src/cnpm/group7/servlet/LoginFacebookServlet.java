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
//			7.7. RestFB.getToken(code) - He thong gui yeu cau lay access token tu code duoc gui ve
			String accessToken = RestFB.getToken(code);

//			7.8. RestFB.getUserInfo(accessToken) - He thong gui yeu cau lay thong tin nguoi dung tu access Token
			User user = RestFB.getUserInfo(accessToken);
			request.setAttribute("id", user.getId());
			request.setAttribute("name", user.getName());
			request.setAttribute("email", user.getEmail());
			
			java.sql.Connection conn = (java.sql.Connection) request.getAttribute("connection");
			
			AccountDAO aDAO = new AccountDAO(conn);
			
			try {
//				7.9. getFacebookAccount(Id) - Truy van xem co tai khoan nao ton tai ung voi Id nay khong?
				Account account = aDAO.getFacebookAccount(user.getId());

//				Alternative flow - Khong co tai khoan tuong ung voi Id nay
				if (account == null) {

//					7.9.1. Tao tai khoan moi voi email va Id facebook
					account = new Account();
					account.setEmail(user.getEmail());
					account.setId_facebook(user.getId());
//					7.9.2. Them tai khoan nay vao Database
					aDAO.insertAccount(account);
				}
				System.out.println(account.getId()+"\t"+account.getEmail()+ "\t"+account.getId_facebook());
//				7.9.10. Them tai khoan vao session moi
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
//			Chuyen huong ve trang index.
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
