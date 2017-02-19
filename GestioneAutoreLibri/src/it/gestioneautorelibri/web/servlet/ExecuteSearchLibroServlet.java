package it.gestioneautorelibri.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class ExecuteSearchLibroServlet
 */
public class ExecuteSearchLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSearchLibroServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destinazione = null;

		String titoloLibro = request.getParameter("titoloInputForm");
		String nPagineLibro = request.getParameter("numeroPagineInputForm");
		String descrizioneLibro = request.getParameter("descrizioneInputForm");
		String genereLibro = request.getParameter("genereInputForm");
		String idAutore = request.getParameter("autoriSelect");
		Utility u = new Utility();

		Integer nPagineInt = u.parseIntFromString(nPagineLibro);
		Integer idAutoreInt = u.parseIntFromString(idAutore);

		LibroDAO libroDAO = null;
		Connection connection = null;
		Autore autore = null;
		AutoreDAO autoreDAO = null;
		List<Libro> listaLibri = new ArrayList<Libro>();

		Libro libro = new Libro();

		libro.setTitolo(titoloLibro);
		libro.setnPagine(nPagineInt != null ? nPagineInt : 0);
		libro.setDescrizione(descrizioneLibro);
		libro.setGenere(genereLibro);

		if (idAutoreInt == 0) {

			try {
				connection = MyConnection.getConnection();
				libroDAO = new LibroDAO(connection);

				listaLibri = libroDAO.findByExample(libro);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			request.setAttribute("listaLibroAttributeName", listaLibri);

			destinazione = "resultLibro.jsp";

		} else {

			try {
				connection = MyConnection.getConnection();
				libroDAO = new LibroDAO(connection);
				autoreDAO = new AutoreDAO(connection);

				autore = new Autore();
				autore = autoreDAO.get(idAutoreInt);
				libro.setAutore(autoreDAO.get(idAutoreInt));

				listaLibri = libroDAO.findByExampleLibroAutore(autore, libro);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			request.setAttribute("listaLibroAttributeName", listaLibri);

			destinazione = "resultLibro.jsp";

		}

		RequestDispatcher rd = request.getRequestDispatcher(destinazione);
		rd.forward(request, response);
	}
}
