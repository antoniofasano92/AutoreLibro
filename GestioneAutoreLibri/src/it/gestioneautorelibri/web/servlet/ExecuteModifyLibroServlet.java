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
 * Servlet implementation class ExecuteModifyLibroServlet
 */
public class ExecuteModifyLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteModifyLibroServlet() {
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
		
		String titoloLibro = request.getParameter("titoloInputForm");
		String nPagineLibro = request.getParameter("numeroPagineInputForm");
		String descrizioneLibro = request.getParameter("descrizioneInputForm");
		String genereLibro = request.getParameter("genereInputForm");
		String idLibro =  request.getParameter("idDelLibroHidden");
		String idAutore = request.getParameter("autoriSelect");
		Utility u = new Utility();

		Integer nPagineInt = u.parseIntFromString(nPagineLibro);
		Integer idAutoreInt = u.parseIntFromString(idAutore);
		Integer idLibroInt = u.parseIntFromString(idLibro);
		
		if (titoloLibro.equals("") || descrizioneLibro.equals("") || genereLibro.equals("") || titoloLibro == null || descrizioneLibro == null || genereLibro == null || nPagineInt==null || nPagineInt<0 || nPagineInt==0 || idAutoreInt==null || idAutoreInt<0 || idAutoreInt==0 ) {

			String messaggio = "Campi non completati, Hai sbagliato, riprova.....";
			request.setAttribute("messaggioErrore", messaggio);
			destinazione = "modifyLibro.jsp";

		} else {
			LibroDAO libroDAO = null;
			Connection connection = null;
			try {
				
				connection = MyConnection.getConnection();
				libroDAO = new LibroDAO(connection);

				Libro libro= new Libro();
				
				libro.setTitolo(titoloLibro);
				libro.setnPagine(nPagineInt != null ? nPagineInt : 0);
				libro.setDescrizione(descrizioneLibro);
				libro.setGenere(genereLibro);
				libro.setId_Libro(idLibroInt);
				libro.setAutore_Id(idAutoreInt);
				libroDAO.update(libro);

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
		}
		

		RequestDispatcher rd = request.getRequestDispatcher(destinazione);
		rd.forward(request, response);

	}

}
