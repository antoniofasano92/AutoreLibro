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
 * Servlet implementation class ExecuteModifyAutoreServlet
 */
public class ExecuteModifyAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteModifyAutoreServlet() {
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

		String nomeAutore = request.getParameter("nomeInputEditForm");
		String cognomeAutore = request.getParameter("cognomeInputEditForm");
		String etaAutore = request.getParameter("etaInputEditForm");
		String casaEditriceAutore = request.getParameter("casaEditriceInputEditForm");
		String partitaIvaAutore = request.getParameter("partitaIvaInputEditForm");
		String idAutore = request.getParameter("idInputEditForm");
		Utility u = new Utility();

		Integer etaAutoreInt = u.parseIntFromString(etaAutore);
		Integer idAutoreInt = u.parseIntFromString(idAutore);

		Autore autore = new Autore();

		if (nomeAutore.equals("") || cognomeAutore.equals("") || casaEditriceAutore.equals("")
				|| partitaIvaAutore.equals("") || etaAutoreInt == null || etaAutoreInt <= 0) {

			autore.setId_Autore(idAutoreInt);

			String messaggio = "Campi non completati, Hai sbagliato, riprova.....";
			request.setAttribute("messaggioErrore", messaggio);
			destinazione = "edit.jsp";

		} else {
			AutoreDAO autoreDAO = null;
			Connection connection = null;

			try {
				connection = MyConnection.getConnection();
				autoreDAO = new AutoreDAO(connection);

				autore.setNome(nomeAutore);
				autore.setCognome(cognomeAutore);
				autore.setEta(etaAutoreInt);
				autore.setCasaEditrice(casaEditriceAutore);
				autore.setPartitaIva(partitaIvaAutore);
				autore.setId_Autore(idAutoreInt);

				autoreDAO.update(autore);
				destinazione = "resultAutore.jsp";
				request.setAttribute("listaAutoreAttributeName", autoreDAO.list());

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
