package com.projets.oc.projet_10_batch.model;

public class Livre {

	private Long id;

	private String titre;

	private Auteur auteur;

	private String genre;

	private int nbreExemplaires;

	private int nbreExemplairesTotal;

	public Livre() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNbreExemplaires() {
		return nbreExemplaires;
	}


	public int getNbreExemplairesTotal() {
		return this.nbreExemplairesTotal;
	}

	public void setNbreExemplaires(int nbreExemplaires) {
		this.nbreExemplaires = nbreExemplaires;
	}

	public void setNbreExemplairesTotal(int nbreExemplairesTotal) {
		this.nbreExemplairesTotal = nbreExemplairesTotal;
	}

}
