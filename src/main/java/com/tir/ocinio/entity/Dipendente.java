package com.tir.ocinio.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Dipendente implements UserDetails{

	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String cf;
	
	private String email;
	
	private String password;
	
	private String telefono;
	
	private Ruolo ruolo;
	
	private Boolean registrato;
	
	private Boolean attivo;
	
	public Dipendente() {}

	public Dipendente(Long id, String nome, String cognome, String cf, String email, String password, String telefono,
			Ruolo ruolo, Boolean registrato, Boolean attivo) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.ruolo = ruolo;
		this.registrato = registrato;
		this.attivo = attivo;
	}
	
	public Dipendente(Long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Boolean getRegistrato() {
		return registrato;
	}

	public void setRegistrato(Boolean registrato) {
		this.registrato = registrato;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	@Override
	public String toString() {
		return "Dipendente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", cf=" + cf + ", email=" + email
				+ ", password=" + password + ", telefono=" + telefono + ", ruolo=" + ruolo
				+ ", registrato=" + registrato + ", attivo=" + attivo + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(ruolo.getAnzianita().nome));
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
