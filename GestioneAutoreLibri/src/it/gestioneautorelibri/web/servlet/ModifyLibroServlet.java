package it.gestioneautorelibri.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestioneautorelibri.dao.AutoreDAO;
import it.gestioneautorelibri.dao.LibroDAO;
import it.gestioneautorelibri.dao.MyConnection;
import it.gestioneautorelibri.model.Autore;
import it.gestioneautorelibri.model.Libro;
import it.gestioneautorelibri.utility.Utility;


/**
 * Servlet implementation class ModifyLibroServlet
 */
public class ModifyLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String parametroIdLibro=request.getParameter("idDelLibroDaInviareComeParametro");
		Utility u = new Utility();
		
		Integer idLibroInt= u.parseIntFromString(parametroIdLibro);
		
		String destinazione = null;
		LibroDAO libroDAO = null;
		AutoreDAO autoreDAO=null;
		Connection connection = null;
		
		try {
			
			connection = MyConnection.getConnection();
			libroDAO = new LibroDAO(connection);
			Libro libro=libroDAO.singoloLibroConAutoreCompleto(idLibroInt);
			
			autoreDAO = new AutoreDAO(connection);
			List <Autore> listAutori= autoreDAO.list();
			
			request.setAttribute("idDelLibroDaInviareComeParametroModify", libro);
			request.setAttribute("listAutoriDaInviarePerIlSelectOption", listAutori);
			destinazione = "modifyLibro.jsp";
			
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
