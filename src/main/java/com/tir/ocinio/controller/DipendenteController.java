package com.tir.ocinio.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tir.ocinio.entity.Dipendente;
import com.tir.ocinio.service.DipendenteService;

@RestController
@RequestMapping("api/dipendente")
public class DipendenteController extends Controller{

	@Autowired
	private DipendenteService dipService;
	
	public DipendenteController() {
		this.format = "{id, nome, cognome,cf,email,telefono,ruolo:{id, anzianita},registrato,attivo}";
	}
	@GetMapping("/all")
	public ResponseEntity<List<HashMap<String, Object>>> getAllDipendenti() {
		var all = dipService.getAllDipendenti();
		var allMap = serializer.serializeAll(format, all);
		return ResponseEntity.ok(allMap);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HashMap<String, Object>> getDipendenteById(@PathVariable("id") Long id) {
		var dip = dipService.getDipendenteById(id);
		var getMap = serializer.serialize(format, dip);
		return ResponseEntity.ok(getMap);
	}
	

	
	@PutMapping("")
	public ResponseEntity<HashMap<String, Object>> updateDipendente(@RequestBody Dipendente dip) {
		dip = dipService.updateDipendente(dip);
		var upMap = serializer.serialize(format, dip);
		return ResponseEntity.ok(upMap);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDipendente(@PathVariable("id") Long id) {
		dipService.deleteDipendente(id);
		return ResponseEntity.ok(null);
	}
	

}
