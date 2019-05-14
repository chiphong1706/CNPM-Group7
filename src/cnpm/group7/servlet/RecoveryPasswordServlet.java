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
	
	//5.7.5 Há»‡ thá»‘ng tiáº¿p nháº­n email
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		AccountDAO accountDAO = new AccountDAO(cnn);
		String email = request.getParameter("email");
		String url = "/";
		try {
			//5.7.6 Há»‡ thá»‘ng yÃªu cáº§u cÆ¡ sá»Ÿ dá»¯ liá»‡u kiá»ƒm tra email
			//5.7.8 CÆ¡ sá»Ÿ dá»¯ liá»‡u tráº£ vá»� káº¿t quáº£ cho há»‡ thá»‘ng
			if (accountDAO.isExistedEmail(email)) {
				//5.7.9	Náº¿u email tá»“n táº¡i, há»‡ thá»‘ng yÃªu cáº§u cÆ¡ sá»Ÿ dá»¯ liá»‡u láº¥y ra máº­t kháº©u cá»§a email tÆ°Æ¡ng á»©ng
				//5.7.11 CÆ¡ sá»Ÿ dá»¯ liá»‡u tráº£ vá»� máº­t kháº©u
				String password = accountDAO.getPassword(email);
				//5.7.12 Há»‡ thá»‘ng láº¥y máº­t kháº©u gá»­i vÃ o mail ngÆ°á»�i dÃ¹ng
				SendMail.sendMail(email, "MÃ¡ÂºÂ­t khÃ¡ÂºÂ©u khÄ‚Â´i phÃ¡Â»Â¥c", "ChÄ‚Â o bÃ¡ÂºÂ¡n, mÃ¡ÂºÂ­t khÃ¡ÂºÂ©u Ã„â€˜Ã„Æ’ng nhÃ¡ÂºÂ­p cÃ¡Â»Â§a bÃ¡ÂºÂ¡n lÄ‚Â : " + password);
				//5.7.13 Há»‡ thá»‘ng thÃ´ng bÃ¡o khÃ´i phá»¥c máº­t kháº©u thÃ nh cÃ´ng
				url = "/recovery-success.jsp";
			} else {
				//5.8 Náº¿u email khÃ´ng tá»“n táº¡i, há»‡ thá»‘ng thÃ´ng bÃ¡o email khÃ´ng tá»“n táº¡i
				url = "/recovery-failure.jsp";
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		
	}


}
