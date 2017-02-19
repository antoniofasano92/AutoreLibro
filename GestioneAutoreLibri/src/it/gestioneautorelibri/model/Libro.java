package it.gestioneautorelibri.model;

public class Libro {
	
	private String titolo;
	private int nPagine;
	private String descrizione;
	private String genere;
	private int id_Libro;
	private int autore_Id;
	private Autore autore;
	
	public Libro(){
		
	}
	
	public Libro(String titolo, int nPagine, String descrizione, String genere, int id_Libro, Autore autore) {
		super();
		this.titolo = titolo;
		this.nPagine = nPagine;
		this.descrizione = descrizione;
		this.genere = genere;
		this.id_Libro = id_Libro;
		this.autore = autore;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getnPagine() {
		return nPagine;
	}

	public void setnPagine(int nPagine) {
		this.nPagine = nPagine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public int getId_Libro() {
		return id_Libro;
	}

	public void setId_Libro(int id_Libro) {
		this.id_Libro = id_Libro;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public int getAutore_Id() {
		return autore_Id;
	}

	public void setAutore_Id(int autore_Id) {
		this.autore_Id = autore_Id;
	}
	
	

}
