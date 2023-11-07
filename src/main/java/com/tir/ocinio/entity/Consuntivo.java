package com.tir.ocinio.entity;

import java.time.LocalDateTime;

public class Consuntivo {

	private Long id;
	private LocalDateTime orarioInizio;
	private LocalDateTime orarioFine;
	private String tipologia;
	private Dipendente dipendente;

	public Consuntivo(LocalDateTime orarioInizio, LocalDateTime orarioFine, String tipologia,
			Dipendente dipendente) {
		super();
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.tipologia = tipologia;
		this.dipendente = dipendente;
	}

	public Consuntivo() {

	}
	
	public Consuntivo(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getOrarioInizio() {
		return orarioInizio;
	}

	public void setOrarioInizio(LocalDateTime orarioInizio) {
		this.orarioInizio = orarioInizio;
	}

	public LocalDateTime getOrarioFine() {
		return orarioFine;
	}

	public void setOrarioFine(LocalDateTime orarioFine) {
		this.orarioFine = orarioFine;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	@Override
	public String toString() {
		return "Consuntivo [id=" + id + ", orario_inizio=" + orarioInizio + ", orario_fine=" + orarioFine
				+ ", tipologia=" + tipologia + ", id_dipendente=" + dipendente + "]";
	}



}
