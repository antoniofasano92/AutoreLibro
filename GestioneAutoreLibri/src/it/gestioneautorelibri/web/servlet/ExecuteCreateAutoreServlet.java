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
 * Servlet implementation class ExecuteCreateAutoreServlet
 */
public class ExecuteCreateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteCreateAutoreServlet() {
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

		String nomeAutore = request.getParameter("nomeInputCreateForm");
		String cognomeAutore = request.getParameter("cognomeInputCreateForm");
		String etaAutore= request.getParameter("etaInputCreateForm");
		String casaEditriceAutore= request.getParameter("casaEditriceInputCreateForm");
		String partitaIvaAutore= request.getParameter("partitaIvaInputCreateForm");
		Utility u = new Utility();
		
		Integer etaInt = u.parseIntFromString(etaAutore);

		if (nomeAutore.equals("") || cognomeAutore.equals("") || casaEditriceAutore.equals("") ||  partitaIvaAutore.equals("") || 
				nomeAutore == null || cognomeAutore == null || casaEditriceAutore == null || etaInt==null || etaInt<0 || partitaIvaAutore==null ) {

			String messaggio = "Campi non completati, Hai sbagliato, riprova.....";
			request.setAttribute("messaggioErrore", messaggio);
			destinazione = "createAutore.jsp";

		} else {
			AutoreDAO autoreDAO = null;
			Connection connection = null;
			try {
				connection = MyConnection.getConnection();
				autoreDAO = new AutoreDAO(connection);

				Autore autore= new Autore();
				
				autore.setNome(nomeAutore);
				autore.setCognome(cognomeAutore);
				autore.setEta(etaInt!=null?etaInt:0);
				autore.setCasaEditrice(casaEditriceAutore);
				autore.setPartitaIva(partitaIvaAutore);
				
				autoreDAO.insert(autore);
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
