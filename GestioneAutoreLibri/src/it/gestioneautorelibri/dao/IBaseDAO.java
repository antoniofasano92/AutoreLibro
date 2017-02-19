package it.gestioneautorelibri.dao;

import java.sql.Connection;
import java.util.*;

public interface IBaseDAO {

	
	public  List list() throws Exception;
	public Object get(int id)throws Exception;
	public int update(Object o)throws Exception;
	public int insert(Object o)throws Exception;
	public int delete(Object o)throws Exception;
	public List findByExample(Object o)throws Exception;
	
}
