package it.gestioneautorelibri.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.gestioneautorelibri.utility.Utility;
import it.gestioneautorelibri.dao.MyConnection;
import it.gestioneautorelibri.dao.AutoreDAO;
import it.gestioneautorelibri.dao.LibroDAO;
import it.gestioneautorelibri.model.Autore;
import it.gestioneautorelibri.model.Libro;

/**
 * Servlet implementation class ExecuteDeleteAutoreLibriServlet
 */
public class ExecuteDeleteAutoreLibriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteDeleteAutoreLibriServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String destinazione = null;
		String parametroIdDelete = request.getParameter("idDaInviareComeParametro");
		Utility u = new Utility();
		Integer idInt = u.parseIntFromString(parametroIdDelete);

		Connection connection = null;
		AutoreDAO autoreDAO = null;
		Autore autore = new Autore();

		try {
			connection = MyConnection.getConnection();
			autoreDAO = new AutoreDAO(connection);

			autore = autoreDAO.get(idInt);
			autoreDAO.populateLibro(autore);

			for (Libro temp : autore.getListaLibri()) {
				LibroDAO libroDAO = new LibroDAO(connection);
				libroDAO.delete(temp);
			}

			autoreDAO.delete(autore);

			request.setAttribute("listaAutoreAttributeName", autoreDAO.list());
			destinazione = "resultAutore.jsp";

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
