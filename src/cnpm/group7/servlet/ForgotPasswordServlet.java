package cnpm.group7.servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AccountDAO;
import tool.SendMail;

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
	
	//5.	Hệ thống tiếp nhận yêu cầu
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String url = "/";
		try {
			//6.	Hệ thống yêu cầu cơ sở dữ liệu kiểm tra email
			//8.	Cơ sở dữ liệu trả về kết quả cho hệ thống
			if (AccountDAO.checkEmail(email)) {
				//9.	Nếu email tồn tại, hệ thống yêu cầu cơ sở dữ liệu lấy ra mật khẩu của email tương ứng
				String password = AccountDAO.getPassword(email);
				//11.	Hệ thống lấy mật khẩu gửi vào mail người dùng
				SendMail.sendMail(email, "Mật khẩu khôi phục", "Chào bạn, mật khẩu đăng nhập của bạn là: " + password);
				//12.	Hệ thống thông báo khôi phục mật khẩu thành công
				url = "/recovery-success.jsp";
			} else {
				//13.	Nếu email không tồn tại, hệ thống thông báo email không tồn tại
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
