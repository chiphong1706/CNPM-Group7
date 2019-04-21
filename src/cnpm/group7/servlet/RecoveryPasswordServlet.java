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
	
	//5.	Há»‡ thá»‘ng tiáº¿p nháº­n yĂªu cáº§u
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cnn = (Connection) request.getAttribute("connection");
		AccountDAO accountDAO = new AccountDAO(cnn);
		String email = request.getParameter("email");
		String url = "/";
		try {
			//6.	Há»‡ thá»‘ng yĂªu cáº§u cÆ¡ sá»Ÿ dá»¯ liá»‡u kiá»ƒm tra email
			//8.	CÆ¡ sá»Ÿ dá»¯ liá»‡u tráº£ vá»� káº¿t quáº£ cho há»‡ thá»‘ng
			if (accountDAO.isExistedEmail(email)) {
				//9.	Náº¿u email tá»“n táº¡i, há»‡ thá»‘ng yĂªu cáº§u cÆ¡ sá»Ÿ dá»¯ liá»‡u láº¥y ra máº­t kháº©u cá»§a email tÆ°Æ¡ng á»©ng
				String password = accountDAO.getPassword(email);
				//11.	Há»‡ thá»‘ng láº¥y máº­t kháº©u gá»­i vĂ o mail ngÆ°á»�i dĂ¹ng
				SendMail.sendMail(email, "Máº­t kháº©u khĂ´i phá»¥c", "ChĂ o báº¡n, máº­t kháº©u Ä‘Äƒng nháº­p cá»§a báº¡n lĂ : " + password);
				//12.	Há»‡ thá»‘ng thĂ´ng bĂ¡o khĂ´i phá»¥c máº­t kháº©u thĂ nh cĂ´ng
				url = "/recovery-success.jsp";
			} else {
				//13.	Náº¿u email khĂ´ng tá»“n táº¡i, há»‡ thá»‘ng thĂ´ng bĂ¡o email khĂ´ng tá»“n táº¡i
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
