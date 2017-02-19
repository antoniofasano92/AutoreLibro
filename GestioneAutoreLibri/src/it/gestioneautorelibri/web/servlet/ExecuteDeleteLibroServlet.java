package it.gestioneautorelibri.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestioneautorelibri.dao.LibroDAO;
import it.gestioneautorelibri.dao.MyConnection;
import it.gestioneautorelibri.model.Libro;
import it.gestioneautorelibri.utility.Utility;

/**
 * Servlet implementation class ExecuteDeleteLibroServlet
 */
public class ExecuteDeleteLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDeleteLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destinazione = null;
		String idLibro = request.getParameter("idLibroDaCencellareDalDatabase");
		Utility u = new Utility();

		Integer idLibroInt = u.parseIntFromString(idLibro);

		LibroDAO libroDAO = null;
		Connection connection = null;

		try {
			connection = MyConnection.getConnection();
			libroDAO = new LibroDAO(connection);

			Libro libroDelete = new Libro();
			libroDelete.setId_Libro(idLibroInt);

			libroDAO.delete(libroDelete);
			destinazione = "resultLibro.jsp";
			request.setAttribute("listaLibroAttributeName", libroDAO.list());

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
