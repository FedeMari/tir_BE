package com.tir.ocinio.entity;

public class Assegnazione {
	
	private Long id;
	
	private Dipendente dipendente;
	
	private Commessa commessa;
	
	private Long competenza;
	
	private Boolean attivo;
	
	public Assegnazione() {
		
	}
	
	public Assegnazione(Long id) {
		this.id = id;
	}

	public Assegnazione(Long id, Dipendente dipendente, Commessa commessa, Long competenza, Boolean attivo) {
		this.id = id;
		this.dipendente = dipendente;
		this.commessa = commessa;
		this.competenza = competenza;
		this.attivo = attivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public Commessa getCommessa() {
		return commessa;
	}

	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	public Long getCompetenza() {
		return competenza;
	}

	public void setCompetenza(Long competenza) {
		this.competenza = competenza;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	@Override
	public String toString() {
		return "Assegnazione [id=" + id + ", dipendente=" + dipendente + ", commessa=" + commessa + ", competenza="
				+ competenza + ", attivo=" + attivo + "]";
	}
	
}
