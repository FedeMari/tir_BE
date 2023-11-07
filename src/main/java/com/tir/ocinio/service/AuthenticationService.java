package com.tir.ocinio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.tir.ocinio.entity.AuthenticationResponse;
import com.tir.ocinio.entity.Dipendente;
import com.tir.ocinio.exception.NonRegistratoException;
import com.tir.ocinio.repository.dao.DipendenteDAO;
@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authentication;
	
	@Autowired
	private DipendenteDAO dipDao;
	
	@Autowired
	private JWTService jwtService;
	
	
	
	/*
	 * questo è il metodo che autentica e confronta l'utente passato da parametro
	 *  con il dipendente da database
	 */
	public AuthenticationResponse authenticate(Dipendente dip) throws NonRegistratoException {
		var dipendente = dipDao.getByEmail(dip.getEmail());
		if(!dipendente.getRegistrato() || !dipendente.getAttivo()) {
			throw new NonRegistratoException();
		}
		authentication.authenticate(new UsernamePasswordAuthenticationToken(dip.getEmail(), dip.getPassword()));
		
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setToken(jwtService.generateToken(dipendente));
		authResponse.setDipendente(dipendente);
		return authResponse;
	}
}
