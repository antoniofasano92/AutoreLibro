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
 * Servlet implementation class ExecuteSearchAutoreServlet
 */
public class ExecuteSearchAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchAutoreServlet() {
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

		String nomeAutore = request.getParameter("nomeInputForm");
		String cognomeAutore = request.getParameter("cognomeInputForm");
		String etaAutore= request.getParameter("etaInputForm");
		String casaEditriceAutore= request.getParameter("casaEditriceInputForm");
		String partitaIvaAutore= request.getParameter("partitaIvaInputForm");
		Utility u = new Utility();
		
		Integer etaInt = u.parseIntFromString(etaAutore);

			AutoreDAO autoreDAO = null;
			Connection connection = null;

			try {
				connection = MyConnection.getConnection();
				autoreDAO = new AutoreDAO(connection);

				Autore autore = new Autore();
				
				autore.setNome(nomeAutore);
				autore.setCognome(cognomeAutore);
				autore.setEta(etaInt!=null?etaInt:0);
				autore.setCasaEditrice(casaEditriceAutore);
				autore.setPartitaIva(partitaIvaAutore);


				request.setAttribute("listaAutoreAttributeName", autoreDAO.findByExample(autore));
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
		//}

		RequestDispatcher rd = request.getRequestDispatcher(destinazione);
		rd.forward(request, response);

	}

}
