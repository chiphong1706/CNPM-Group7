package cnpm.group7.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cnpm.group7.DAO.AccountDAO;
import cnpm.group7.tool.SendMail;

/**
 * Servlet implementation class RecoveryServlet
 */
@WebServlet("/recovery-password")
public class RecoveryPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecoveryPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//5.7.5 Hệ thống tiếp nhận email
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		AccountDAO accountDAO = new AccountDAO(cnn);
		String email = request.getParameter("email");
		String url = "/";
		try {
			//5.7.6 Hệ thống yêu cầu cơ sở dữ liệu kiểm tra email
			//5.7.8 Cơ sở dữ liệu trả về kết quả cho hệ thống
			if (accountDAO.isExistedEmail(email)) {
				//5.7.9	Nếu email tồn tại, hệ thống yêu cầu cơ sở dữ liệu lấy ra mật khẩu của email tương ứng
				//5.7.11 Cơ sở dữ liệu trả về mật khẩu
				String password = accountDAO.getPassword(email);
				//5.7.12 Hệ thống lấy mật khẩu gửi vào mail người dùng
				SendMail.sendMail(email, "Máº­t kháº©u khĂ´i phá»¥c", "ChĂ o báº¡n, máº­t kháº©u Ä‘Äƒng nháº­p cá»§a báº¡n lĂ : " + password);
				//5.7.13 Hệ thống thông báo khôi phục mật khẩu thành công
				url = "/recovery-success.jsp";
			} else {
				//5.8 Nếu email không tồn tại, hệ thống thông báo email không tồn tại
				url = "/recovery-failure.jsp";
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		
	}


}
