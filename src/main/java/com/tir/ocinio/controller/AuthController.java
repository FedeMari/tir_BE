package com.tir.ocinio.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tir.ocinio.entity.AuthenticationResponse;
import com.tir.ocinio.entity.Dipendente;
import com.tir.ocinio.exception.NonRegistratoException;
import com.tir.ocinio.service.AuthenticationService;
import com.tir.ocinio.service.DipendenteService;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends Controller {
	
	@Autowired
	private AuthenticationService auth;
	
	@Autowired
	private DipendenteService dipService;
	
	public AuthController() {
		this.format = "{id, nome, cognome,cf,email,telefono,ruolo:{id, anzianita},registrato,attivo}";
	}
	
	@PostMapping("/login")
	private ResponseEntity<HashMap<String, Object>> auth (@RequestBody Dipendente dip) throws NonRegistratoException{
		var autenticato = auth.authenticate(dip);
		var regMap = serializer.serialize("{token,dipendente:{id,ruolo:{anzianita}}}", autenticato);
		return ResponseEntity.ok(regMap);
		
	}
	@PostMapping("/registrazione")
	public ResponseEntity<HashMap<String, Object>> registerDipendente(@RequestBody Dipendente dip){
		dip = dipService.registerDipendente(dip);
		var regMap = serializer.serialize(format, dip);
		return ResponseEntity.ok(regMap);
	}
	@PostMapping("/confirm/{token}")
	public ResponseEntity<Object> confirmRegistration(@PathVariable("token") String token){
		dipService.confirmRegistration(token);
		return ResponseEntity.ok(null);
	}
	
	
}
