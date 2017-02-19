package it.gestioneautorelibri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestioneautorelibri.model.User;

public class UserDAO extends AbstractMySQLDAO {
	
	public static final String UTENTE_NOME_TABELLA = "user";
	

	public UserDAO(Connection connection){
		super(connection);
	}

	@Override
	public int insert(Object o) throws Exception {

		if (isNotActive() || o == null || !(o instanceof User)) {
			return -1;
		}

		User u = (User) o;

		PreparedStatement ps = null;
		int result = 0;

		try {

			ps = connection
					.prepareStatement("INSERT INTO "+UTENTE_NOME_TABELLA+" (nome, cognome, username, password) VALUES (?, ?, ?, ?);");
			ps.setString(1, u.getNome());
			ps.setString(2, u.getCognome());
			ps.setString(2, u.getUsername());
			ps.setString(2, u.getPassword());
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
	public int delete(Object o) throws Exception {
		
		if (isNotActive()|| o == null || !(o instanceof User)) {
			return -1;
		}
		User u = (User) o;

		PreparedStatement ps = null;
		int result = 0;

		try {

			ps = connection.prepareStatement("DELETE FROM "+UTENTE_NOME_TABELLA+" WHERE ID="
					+ u.getId_user());
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
	public List<User> findByExample(Object o) throws Exception {

		if (isNotActive() || o == null || !(o instanceof User)) {
			return null;
		}
		ArrayList<User> result = new ArrayList<User>();
		User u = (User) o;

		Statement ps = null;
		ResultSet rs = null;

		String query = "select * from "+UTENTE_NOME_TABELLA+" where 1=1 ";
		if (u.getCognome() != null && !u.getCognome().equals("")) {
			query += " and cognome='" + u.getCognome() + "' ";
		}
		if (u.getNome() != null && !u.getNome().equals("")) {
			query += " and nome='" + u.getNome() + "' ";
		}

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery(query);

			while (rs.next()) {
				u = new User();
				u.setNome(rs.getString("NOME"));
				u.setCognome(rs.getString("COGNOME"));
				u.setId_user(rs.getInt("ID"));
				result.add(u);
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
	public User get(int id) throws Exception {
		if (isNotActive() || id<1) {
			return null;
		}
		Statement ps = null;
		ResultSet rs = null;
		User u = null;

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from "+UTENTE_NOME_TABELLA+" where id=" + id);
			System.out.println(ps);

			if (rs.next()) {
				u = new User();
				u.setNome(rs.getString("NOME"));
				u.setCognome(rs.getString("COGNOME"));
				u.setId_user(rs.getInt("ID"));
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
		return u;
	}

	@Override
	public List<User> list() throws Exception {

		if (isNotActive()) {
			return null;
		}
		Statement ps = null;
		ResultSet rs = null;
		ArrayList<User> arr = new ArrayList<User>();
		User u = null;

		try {

			ps = connection.createStatement();
			rs = ps.executeQuery("select * from "+UTENTE_NOME_TABELLA+"");

			while (rs.next()) {
				u = new User();
				u.setNome(rs.getString("NOME"));
				u.setCognome(rs.getString("COGNOME"));
				u.setId_user(rs.getInt("ID"));
				arr.add(u);

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
		if (isNotActive() || o == null || !(o instanceof User)) {
			return -1;
		}

		User u = (User) o;

		PreparedStatement ps = null;
		int result = 0;

		try {

			ps = connection
					.prepareStatement("UPDATE "+UTENTE_NOME_TABELLA+" SET nome=?, cognome=? where id=?;");
			ps.setString(1, u.getNome());
			ps.setString(2, u.getCognome());
			ps.setInt(3, u.getId_user());
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

	public boolean accedi(User user) throws Exception {
	
		Statement ps = null;
		ResultSet rs = null;
		boolean puoiAccedere=false;

		String query = ("select * from user where username='" + user.getUsername() + "' and password='"+ user.getPassword()+"'");
		
		try {
			
			ps = connection.createStatement();
			rs = ps.executeQuery(query);
			
			if (rs.next()) {
				puoiAccedere=true;
				
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
		return puoiAccedere;
	}



}
