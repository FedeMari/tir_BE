package com.tir.ocinio.entity;

import java.sql.Date;

public class Commessa {

	private Long id;
	private String tipo;
	private Long durata;
	private String descrizione;
	private Float importoContratto;
	private Date dataInizio;
	private Date dataFine;
	private Cliente cliente;
	private Boolean attivo;

	public Commessa(long id) {
		this.id = id;
	}

	public Commessa(){}
	
	public Commessa(Long id, String tipo, Long durata, String descrizione, Float importoContratto, Date dataInizio,
			Date dataFine, Cliente cliente, boolean attivo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.durata = durata;
		this.descrizione = descrizione;
		this.importoContratto = importoContratto;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.cliente = cliente;
		this.attivo = attivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getDurata() {
		return durata;
	}

	public void setDurata(Long durata) {
		this.durata = durata;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Float getImportoContratto() {
		return importoContratto;
	}

	public void setImportoContratto(Float importoContratto) {
		this.importoContratto = importoContratto;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}
}
