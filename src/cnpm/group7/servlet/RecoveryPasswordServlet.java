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
	
	//4.7.8 He thong tiep nhan email
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//4.7.9 He thong yeu cau ket noi den co so du lieu
		//4.7.10 CSDL tra ve ket noi
		Connection connection = (Connection) request.getAttribute("connection");
		//4.7.11 He thong tao doi tuong thao tac voi CSDL
		AccountDAO ad = new AccountDAO(connection);
		String email = request.getParameter("email");
		String url = "/";
		try {
			//4.7.12 He thong yeu cau CSDL kiem tra email
			//4.7.14 CSDL tra ve ket qua cho he thong
			if (ad.isExistedEmail(email)) {
				//4.7.15 Neu email da dang ky, he thong yeu cau CSDL lay ra mat khau cua email tuong ung
				String password = ad.getPassword(email);
				//4.7.17 CSDL tra ve mat khau
				//4.7.18 He thong lay mat khau gui vao mail nguoi dung
				SendMail.sendMail(email, "Mat khau khoi phuc", "Chao ban, mat khau dang nhap cua ban la: " + password);
				//4.7.19 He thong chuyen huong den trang khoi phuc thanh cong
				url = "/recovery-success.jsp";
			} else {
				//4.7.21 Neu email khong ton tai, he thong chuyen huong den trang khoi phuc that bai
				url = "/recovery-failure.jsp";
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		
	}


}
