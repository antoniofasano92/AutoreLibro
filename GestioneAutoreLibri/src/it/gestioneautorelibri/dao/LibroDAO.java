package it.gestioneautorelibri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestioneautorelibri.model.Autore;
import it.gestioneautorelibri.model.Libro;


public class LibroDAO extends AbstractMySQLDAO {
	
	public static final String LIBRO_NOME_TABELLA = "libro";
	

	public LibroDAO(Connection connection){
		super(connection);
	}

	@Override
	public int delete(Object o) throws Exception {
		
		if (isNotActive()|| o == null || !(o instanceof Libro)) {
			return -1;
		}
		Libro libro = (Libro) o;

		PreparedStatement ps = null;
		int result = 0;

		try {

			ps = connection.prepareStatement("DELETE FROM "+LIBRO_NOME_TABELLA+" WHERE id_libro="
					+ libro.getId_Libro());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			try {
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return result;
	}

	@Override
	public List<Libro> findByExample(Object o) throws Exception {

		if (isNotActive() || o == null || !(o instanceof Libro)) {
			return null;
		}
		ArrayList<Libro> result = new ArrayList<Libro>();
		Libro libro = (Libro) o;

		Statement ps = null;
		ResultSet rs = null;

		String query = "select * from libro l inner join autore a on l.autore_id = a.id_autore ";
		
		if (libro.getTitolo() != null && !libro.getTitolo().equals("")) {
			query += " and titolo='" + libro.getTitolo() + "' ";
		}
		if (libro.getnPagine()>0) {
			query += " and npagine='" + libro.getnPagine() + "' ";
		}
		if (libro.getDescrizione() != null && !libro.getDescrizione().equals("")) {
			query += " and descrizione='" + libro.getDescrizione() + "' ";
		}
	
		if (libro.getGenere() != null && !libro.getGenere().equals("")) {
			query += " and genere='" + libro.getGenere() + "' ";
		}
		query += " ;";

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery(query);

			while (rs.next()) {
				libro = new Libro();
				libro.setTitolo(rs.getString("l.titolo"));
				libro.setnPagine(rs.getInt("l.npagine"));
				libro.setDescrizione(rs.getString("l.descrizione"));
				libro.setGenere(rs.getString("l.genere"));
				libro.setId_Libro(rs.getInt("l.id_libro"));
				
				Autore autore = new Autore();
				autore.setNome(rs.getString("a.nome"));
				autore.setCognome(rs.getString("a.cognome"));
				autore.setEta(rs.getInt("a.eta"));
				autore.setCasaEditrice(rs.getString("a.casaeditrice"));
				autore.setPartitaIva(rs.getString("a.partitaiva"));
				autore.setId_Autore(rs.getInt("a.id_autore"));
				libro.setAutore(autore);
				
				result.add(libro);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			try {
				rs.close();
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return result;
	}

	@Override
	public Libro get(int id) throws Exception {
		if (isNotActive() || id<1) {
			return null;
		}
		Statement ps = null;
		ResultSet rs = null;
		Libro libro = null;

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from "+LIBRO_NOME_TABELLA+" where id_libro=" + id);
			System.out.println(ps);

			if (rs.next()) {
				libro = new Libro();
				libro.setTitolo(rs.getString("titolo"));
				libro.setnPagine(rs.getInt("npagine"));
				libro.setDescrizione(rs.getString("descrizione"));
				libro.setGenere(rs.getString("genere"));
				libro.setId_Libro(rs.getInt("id_libro"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			try {
				rs.close();
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return libro;
	}

	@Override
	public int insert(Object o) throws Exception {

		if (isNotActive() || o == null || !(o instanceof Libro)) {
			return -1;
		}

		Libro libro = (Libro) o;

		PreparedStatement ps = null;
		int result = 0;

		try {

			ps = connection.prepareStatement("INSERT INTO "+LIBRO_NOME_TABELLA+" (titolo, npagine, descrizione, genere, autore_id) VALUES (?, ?, ?, ?, ?);");
			ps.setString(1, libro.getTitolo());
			ps.setInt(2, libro.getnPagine());
			ps.setString(3, libro.getDescrizione());
			ps.setString(4, libro.getGenere());
			ps.setInt(5, libro.getAutore_Id());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			try {
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return result;
	}


	public List<Libro> listOriginal() throws Exception {

		if (isNotActive()) {
			return null;
		}
		Statement ps = null;
		ResultSet rs = null;
		ArrayList<Libro> arr = new ArrayList<Libro>();
		Libro libro = null;

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from "+LIBRO_NOME_TABELLA+"");

			while (rs.next()) {
				libro = new Libro();
				libro.setTitolo(rs.getString("titolo"));
				libro.setnPagine(rs.getInt("npagine"));
				libro.setDescrizione(rs.getString("descrizione"));
				libro.setGenere(rs.getString("genere"));
				libro.setId_Libro(rs.getInt("id_libro"));
				arr.add(libro);

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			try {
				rs.close();
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return arr;
	}

	@Override
	public int update(Object o) throws Exception {
		if (isNotActive() || o == null || !(o instanceof Libro)) {
			return -1;
		}

		Libro libro = (Libro) o;

		PreparedStatement ps = null;
		int result = 0;

		try {

			ps = connection
					.prepareStatement("UPDATE "+LIBRO_NOME_TABELLA+" SET titolo=?, npagine=?, descrizione=?, genere=?, autore_id=? where id_libro=?;");
			ps.setString(1, libro.getTitolo());
			ps.setInt(2, libro.getnPagine());
			ps.setString(3, libro.getDescrizione());
			ps.setString(4, libro.getGenere());
			ps.setInt(5, libro.getAutore_Id());
			ps.setInt(6, libro.getId_Libro());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			try {
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return result;
	}

	public List<Libro> getLibroAutore(Autore autore) throws Exception {
		
		Statement ps = null;
		ResultSet rs = null;
		Libro libro = null;
		List <Libro> listaLibri= new ArrayList <Libro>();
		

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from "+LIBRO_NOME_TABELLA+" where autore_id = "+autore.getId_Autore());
			
			System.out.println(ps);

			while (rs.next()) {
				libro = new Libro();
				libro.setTitolo(rs.getString("titolo"));
				libro.setnPagine(rs.getInt("npagine"));
				libro.setDescrizione(rs.getString("descrizione"));
				libro.setGenere(rs.getString("genere"));
				libro.setId_Libro(rs.getInt("id_libro"));
				libro.setAutore(autore);
			
				
		
				listaLibri.add(libro);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			try {
				rs.close();
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return listaLibri;
	}
	
	
	public List<Libro> findByExampleLibroAutore(Autore autore, Libro libro) throws Exception {


		ArrayList<Libro> result = new ArrayList<Libro>();

		Statement ps = null;
		ResultSet rs = null;

		String query = "select * from "+LIBRO_NOME_TABELLA+" where autore_id =" +autore.getId_Autore();
		
		if (libro.getTitolo() != null && !libro.getTitolo().equals("")) {
			query += " and titolo='" + libro.getTitolo() + "' ";
		}
		if (libro.getnPagine()>0) {
			query += " and npagine='" + libro.getnPagine() + "' ";
		}
		if (libro.getDescrizione() != null && !libro.getDescrizione().equals("")) {
			query += " and descrizione='" + libro.getDescrizione() + "' ";
		}
	
		if (libro.getGenere() != null && !libro.getGenere().equals("")) {
			query += " and genere='" + libro.getGenere() + "' ";
		}
		
		try {
																
			ps = connection.createStatement();
			rs = ps.executeQuery(query);

			while (rs.next()) {
				libro = new Libro();
				
				libro.setTitolo(rs.getString("titolo"));
				libro.setnPagine(rs.getInt("npagine"));
				libro.setDescrizione(rs.getString("descrizione"));
				libro.setGenere(rs.getString("genere"));
				libro.setId_Libro(rs.getInt("id_libro"));
				libro.setAutore(autore);
				result.add(libro);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			try {
				rs.close();
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return result;
	}
	
	public List<Libro> list() throws Exception {

		if (isNotActive()) {
			return null;
		}
		Statement ps = null;
		ResultSet rs = null;
		ArrayList<Libro> arr = new ArrayList<Libro>();
		Libro libro = null;

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from libro l inner join autore a on l.autore_id= a.id_autore");

			while (rs.next()) {
				libro = new Libro();
				libro.setTitolo(rs.getString("l.titolo"));
				libro.setnPagine(rs.getInt("l.npagine"));
				libro.setDescrizione(rs.getString("l.descrizione"));
				libro.setGenere(rs.getString("l.genere"));
				libro.setId_Libro(rs.getInt("l.id_libro"));
				
				Autore autore = new Autore();
				autore.setNome(rs.getString("a.nome"));
				autore.setCognome(rs.getString("a.cognome"));
				autore.setId_Autore(rs.getInt("a.id_autore"));
				libro.setAutore(autore);
				arr.add(libro);

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			try {
				rs.close();
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return arr;
	}
	
	public Libro singoloLibroConAutoreCompleto(int id) throws Exception {

		if (isNotActive()) {
			return null;
		}
		Statement ps = null;
		ResultSet rs = null;
		Libro libro = null;

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from libro l inner join autore a on l.autore_id= a.id_autore where id_libro="+id);

			if (rs.next()) {
				libro = new Libro();
				libro.setTitolo(rs.getString("l.titolo"));
				libro.setnPagine(rs.getInt("l.npagine"));
				libro.setDescrizione(rs.getString("l.descrizione"));
				libro.setGenere(rs.getString("l.genere"));
				libro.setId_Libro(rs.getInt("l.id_libro"));
				
				Autore autore = new Autore();
				autore.setNome(rs.getString("a.nome"));
				autore.setCognome(rs.getString("a.cognome"));
				autore.setEta(rs.getInt("a.eta"));
				autore.setCasaEditrice(rs.getString("a.casaeditrice"));
				autore.setPartitaIva(rs.getString("a.partitaiva"));
				autore.setId_Autore(rs.getInt("a.id_autore"));
				libro.setAutore(autore);

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			try {
				rs.close();
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return libro;
	}
}