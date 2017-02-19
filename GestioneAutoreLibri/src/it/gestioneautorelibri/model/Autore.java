package it.gestioneautorelibri.model;

import java.util.ArrayList;
import java.util.List;

public class Autore {
	
	private String nome;
	private String cognome;
	private int eta;
	private String casaEditrice;
	private String partitaIva;
	private int id_Autore;
	private List<Libro> listaLibri = new ArrayList<Libro>();
	
	public Autore(){
		
	}
	
	public Autore(String nome, String cognome, int eta, String casaEditrice, String partitaIva, int id_Autore,
			List<Libro> listaLibri) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.casaEditrice = casaEditrice;
		this.partitaIva = partitaIva;
		this.id_Autore = id_Autore;
		this.listaLibri = listaLibri;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getCasaEditrice() {
		return casaEditrice;
	}

	public void setCasaEditrice(String casaEditrice) {
		this.casaEditrice = casaEditrice;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public int getId_Autore() {
		return id_Autore;
	}

	public void setId_Autore(int id_Autore) {
		this.id_Autore = id_Autore;
	}

	public List<Libro> getListaLibri() {
		return listaLibri;
	}

	public void setListaLibri(List<Libro> listaLibri) {
		this.listaLibri = listaLibri;
	}
	
	
	
	

}
