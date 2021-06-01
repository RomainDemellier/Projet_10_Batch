package com.projets.oc.projet_10_batch.model;

import java.time.LocalDateTime;

public class Reservation {

	private Long id;

	private Livre livre;

	private Usager usager;

	private LocalDateTime date;

	private LocalDateTime dateLimit;

	private Boolean actif;

	public Reservation(){
		super();
	}


	public Reservation(Long id, Livre livre, Usager usager, LocalDateTime date, LocalDateTime dateLimit) {
		this.id = id;
		this.livre = livre;
		this.usager = usager;
		this.date = date;
		this.dateLimit = dateLimit;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Livre getLivre() {
		return this.livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public Usager getUsager() {
		return this.usager;
	}

	public void setUsager(Usager usager) {
		this.usager = usager;
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getDateLimit() {
		return this.dateLimit;
	}

	public void setDateLimit(LocalDateTime dateLimit) {
		this.dateLimit = dateLimit;
	}

	public Reservation id(Long id) {
		this.id = id;
		return this;
	}

	public Reservation livre(Livre livre) {
		this.livre = livre;
		return this;
	}

	public Reservation usager(Usager usager) {
		this.usager = usager;
		return this;
	}

	public Reservation date(LocalDateTime date) {
		this.date = date;
		return this;
	}

	public Reservation dateLimit(LocalDateTime dateLimit) {
		this.dateLimit = dateLimit;
		return this;
	}


	public Boolean isActif() {
		return this.actif;
	}

	public Boolean getActif() {
		return this.actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}
}
