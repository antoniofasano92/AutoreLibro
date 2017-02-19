package it.gestioneautorelibri.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.gestioneautorelibri.dao.MyConnection;
import it.gestioneautorelibri.dao.UserDAO;
import it.gestioneautorelibri.model.User;


/**
 * Servlet implementation class AccessoGestioneServlet
 */
public class AccessoGestioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessoGestioneServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destinazione = null;

		String username = request.getParameter("usernameInputCreateForm");
		String password = request.getParameter("passwordInputCreateForm");

			UserDAO userDAO = null;
			Connection connection = null;

			
			try {
				connection = MyConnection.getConnection();
				userDAO = new UserDAO(connection);
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
	
				boolean verifica=userDAO.accedi(user);
				
				if(verifica){
					
					destinazione = "homePage.jsp";
					
				}else{
					
					String messaggio = "Mi dispiace, non sei autorizzato ad accedere";
					request.setAttribute("messaggioErrore", messaggio);
					destinazione = "main.jsp";
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		RequestDispatcher rd = request.getRequestDispatcher(destinazione);
		rd.forward(request, response);

	}
}
