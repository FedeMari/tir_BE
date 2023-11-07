package com.tir.ocinio.entity;

public class TokenRegistrazione {

	private Long idDipendente;
	
	private String token;

	public TokenRegistrazione(Long idDipendente, String token) {
		this.idDipendente = idDipendente;
		this.token = token;
	}

	public Long getIdDipendente() {
		return idDipendente;
	}

	public void setIdDipendente(Long idDipendente) {
		this.idDipendente = idDipendente;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "TokenRegistrazione [id_dipendente=" + idDipendente + ", token=" + token + "]";
	}
	
	
}
