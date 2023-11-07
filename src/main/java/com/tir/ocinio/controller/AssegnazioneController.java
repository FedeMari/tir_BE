package com.tir.ocinio.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.tir.ocinio.entity.Assegnazione;
import com.tir.ocinio.enumeration.Anzianita;
import com.tir.ocinio.service.AssegnazioneService;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/assegnazione")
public class AssegnazioneController extends Controller{

	@Autowired
	AssegnazioneService assService;
	
	
	public AssegnazioneController() {
		this.format = "{id, dipendente:{id, nome, cognome}, commessa:{id}, competenza, attivo}";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HashMap<String, Object>> getAssegnazioneById(@PathVariable("id") Long id){
		var ass = assService.getAsssegnazioneById(id);			
		var assMap = serializer.serialize(format, ass);
		return ResponseEntity.ok(assMap);
	}
	
	@GetMapping("/com/{id}")
	public ResponseEntity<List<HashMap<String, Object>>> getAssegnazioneByIdCom(@PathVariable("id") Long id_com){
		List<Assegnazione> assegnazione = assService.getAssegnazioneByIdCommessa(id_com);
		var assegnazioneList = serializer.serializeAll(format, assegnazione);
		return new ResponseEntity<>(assegnazioneList, HttpStatus.OK);
	}
	
	@GetMapping("/dip/{id}")
	public ResponseEntity<List<HashMap<String, Object>>> getAssegnazioneByIdDip(@PathVariable("id") Long id_dip){
		List<Assegnazione> assegnazione = assService.getAssegnazioneByIdDipendente(id_dip);
		var assegnazioneList = serializer.serializeAll(format, assegnazione);
		return new ResponseEntity<>(assegnazioneList, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	@RolesAllowed("HR")
	public ResponseEntity<List<HashMap<String, Object>>> getAllAssegnazioni() {
		List<Assegnazione> assegnazione = assService.getAllAssegnazioni();
		var assegnazioneList = serializer.serializeAll(format, assegnazione);
		return new ResponseEntity<>(assegnazioneList, HttpStatus.OK);
	}
	
	@GetMapping("/allactive")
	public ResponseEntity<List<HashMap<String, Object>>> getAllAssegnazioniAttive() {
		List<Assegnazione> assegnazione = assService.getAllAssegnazioniAttive();
		var assegnazioneList = serializer.serializeAll(format, assegnazione);
		return new ResponseEntity<>(assegnazioneList, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<HashMap<String, Object>> inserisciAssegnazione(@RequestBody Assegnazione a){
		a = assService.inserisciAssegnazione(a);
		var assMap = serializer.serialize(format, a);
		return  ResponseEntity.ok(assMap);
	}
	
	@PutMapping("")
	public ResponseEntity<HashMap<String, Object>> updateAssegnazione(@RequestBody Assegnazione a){
		a = assService.modificaAssegnazione(a);
		var assMap = serializer.serialize(format, a);
		return ResponseEntity.ok(assMap);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Assegnazione> eliminaAssegnazione(@PathVariable("id") Long id){
		assService.eliminaAssegnazione(id);
		return  ResponseEntity.ok(null);
	}
}
