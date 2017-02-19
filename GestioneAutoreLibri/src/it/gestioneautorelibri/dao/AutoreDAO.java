package it.gestioneautorelibri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestioneautorelibri.model.Autore;
import it.gestioneautorelibri.model.Libro;


public class AutoreDAO extends AbstractMySQLDAO {

	public static final String AUTORE_NOME_TABELLA = "autore";

	public AutoreDAO(Connection connection) {
		super(connection);
	}

	@Override
	public int delete(Object o) throws Exception {

		if (isNotActive() || o == null || !(o instanceof Autore)) {
			return -1;
		}
		Autore autore = (Autore) o;

		PreparedStatement ps = null;
		int result = 0;

		try {

			ps = connection.prepareStatement(
					"DELETE FROM " + AUTORE_NOME_TABELLA + " WHERE id_autore=" + autore.getId_Autore());
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
	public List<Autore> findByExample(Object o) throws Exception {

		if (isNotActive() || o == null || !(o instanceof Autore)) {
			return null;
		}
		ArrayList<Autore> result = new ArrayList<Autore>();
		Autore autore = (Autore) o;

		Statement ps = null;
		ResultSet rs = null;

		String query = "select * from " + AUTORE_NOME_TABELLA + " where 1=1 ";

		if (autore.getNome() != null && !autore.getNome().equals("")) {
			query += " and nome='" + autore.getNome() + "' ";
		}
		if (autore.getCognome() != null && !autore.getCognome().equals("")) {
			query += " and cognome='" + autore.getCognome() + "' ";
		}
		if (autore.getEta() > 0) {
			query += " and eta='" + autore.getEta() + "' ";
		}
		if (autore.getCasaEditrice() != null && !autore.getCasaEditrice().equals("")) {
			query += " and casaeditrice='" + autore.getCasaEditrice() + "' ";
		}
		if (autore.getPartitaIva() != null && !autore.getPartitaIva().equals("")) {
			query += " and partitaiva='" + autore.getPartitaIva() + "' ";
		}

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery(query);

			while (rs.next()) {
				autore = new Autore();
				autore.setNome(rs.getString("NOME"));
				autore.setCognome(rs.getString("COGNOME"));
				autore.setEta(rs.getInt("eta"));
				autore.setCasaEditrice(rs.getString("casaeditrice"));
				autore.setPartitaIva(rs.getString("partitaiva"));
				autore.setId_Autore(rs.getInt("id_autore"));
				result.add(autore);
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
	public Autore get(int id) throws Exception {
		if (isNotActive() || id < 1) {
			return null;
		}
		Statement ps = null;
		ResultSet rs = null;
		Autore autore = null;

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from " + AUTORE_NOME_TABELLA + " where id_autore=" + id);
			System.out.println(ps);

			if (rs.next()) {
				autore = new Autore();
				autore.setNome(rs.getString("NOME"));
				autore.setCognome(rs.getString("COGNOME"));
				autore.setEta(rs.getInt("eta"));
				autore.setCasaEditrice(rs.getString("casaeditrice"));
				autore.setPartitaIva(rs.getString("partitaiva"));
				autore.setId_Autore(rs.getInt("id_autore"));
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
		return autore;
	}

	@Override
	public int insert(Object o) throws Exception {

		if (isNotActive() || o == null || !(o instanceof Autore)) {
			return -1;
		}

		Autore autore = (Autore) o;

		PreparedStatement ps = null;
		int result = 0;

		try {

			ps = connection.prepareStatement("INSERT INTO " + AUTORE_NOME_TABELLA
					+ " (nome, cognome, eta, casaeditrice, partitaiva) VALUES (?, ?, ?, ?, ?);");
			ps.setString(1, autore.getNome());
			ps.setString(2, autore.getCognome());
			ps.setInt(3, autore.getEta());
			ps.setString(4, autore.getCasaEditrice());
			ps.setString(5, autore.getPartitaIva());
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
	public List<Autore> list() throws Exception {

		if (isNotActive()) {
			return null;
		}
		Statement ps = null;
		ResultSet rs = null;
		ArrayList<Autore> arr = new ArrayList<Autore>();
		Autore autore = null;

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from " + AUTORE_NOME_TABELLA + "");

			while (rs.next()) {
				autore = new Autore();
				autore.setNome(rs.getString("NOME"));
				autore.setCognome(rs.getString("COGNOME"));
				autore.setEta(rs.getInt("eta"));
				autore.setCasaEditrice(rs.getString("casaeditrice"));
				autore.setPartitaIva(rs.getString("partitaiva"));
				autore.setId_Autore(rs.getInt("id_autore"));
				arr.add(autore);

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
		if (isNotActive() || o == null || !(o instanceof Autore)) {
			return -1;
		}

		Autore autore = (Autore) o;

		PreparedStatement ps = null;
		int result = 0;

		try {

			ps = connection.prepareStatement("UPDATE " + AUTORE_NOME_TABELLA
					+ " SET nome=?, cognome=?, eta=?, casaeditrice=?, partitaiva=? where id_autore=?;");
			ps.setString(1, autore.getNome());
			ps.setString(2, autore.getCognome());
			ps.setInt(3, autore.getEta());
			ps.setString(4, autore.getCasaEditrice());
			ps.setString(5, autore.getPartitaIva());
			ps.setInt(6, autore.getId_Autore());
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
	
	public void populateLibro(Autore autoreInputDaDB) throws Exception {

		Statement ps = null;
		ResultSet rs = null;

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from libro where autore_id="+ autoreInputDaDB.getId_Autore());

			while (rs.next()) {
	
				Libro libro = new Libro();
				libro.setTitolo(rs.getString("titolo"));
				libro.setnPagine(rs.getInt("npagine"));
				libro.setDescrizione(rs.getString("descrizione"));
				libro.setGenere(rs.getString("genere"));
				libro.setId_Libro(rs.getInt("id_libro"));
				libro.setAutore(autoreInputDaDB);

				autoreInputDaDB.getListaLibri().add(libro);

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

	}


}
