package it.gestioneautorelibri.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestioneautorelibri.dao.AutoreDAO;
import it.gestioneautorelibri.dao.MyConnection;
import it.gestioneautorelibri.model.Autore;
import it.gestioneautorelibri.utility.Utility;

/**
 * Servlet implementation class DettagliAutoreServlet
 */
public class DettagliAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametroIdDettaglio=request.getParameter("idDaInviareComeParametro");
		Utility u = new Utility();
		
		Integer idInt= u.parseIntFromString(parametroIdDettaglio);
		
		String destinazione = null;
		AutoreDAO autoreDAO = null;
		Connection connection = null;
		
		try {
			
			connection = MyConnection.getConnection();
			autoreDAO = new AutoreDAO(connection);
			
			Autore autore=autoreDAO.get(idInt);
			autoreDAO.populateLibro(autore);
			
			request.setAttribute("idAutoreAttributeName", autore);
			destinazione = "visualizzaDettagliAutore.jsp";
			
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
