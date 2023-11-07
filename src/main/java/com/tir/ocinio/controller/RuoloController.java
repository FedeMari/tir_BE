package com.tir.ocinio.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tir.ocinio.entity.Ruolo;
import com.tir.ocinio.service.RuoloService;

@RestController
@RequestMapping("api/ruolo")
public class RuoloController extends Controller{
	
	@Autowired
	private RuoloService ruoService;
	
	public RuoloController () {
		this.format = "{id, compenso, anzianita}";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HashMap<String, Object>> getRuoloById(@PathVariable(value = "id") Long id) {
		
		Ruolo ruolo = ruoService.getRuoloById(id);
		
		var assMap = serializer.serialize(format, ruolo);

		return new ResponseEntity<>(assMap, HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<HashMap<String, Object>>> getAllRuoli(){
		List<Ruolo> ruoli = ruoService.getAllRuoli();
		
		var assMap = serializer.serializeAll(format, ruoli);

		return new ResponseEntity<>(assMap, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<HashMap<String, Object>> insertRuolo(@RequestBody Ruolo ruo){
		
		ruo = ruoService.insertRuolo(ruo);
		
		var assMap = serializer.serialize(format, ruo);

		return new ResponseEntity<>(assMap, HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<HashMap<String, Object>> updateRuolo(@RequestBody Ruolo ruo) {
		
		ruo = ruoService.updateRuolo(ruo);
		
		var assMap = serializer.serialize(format, ruo);

		return new ResponseEntity<>(assMap, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteRuolo(@PathVariable (value = "id") Long id) {
		
		ruoService.deleteRuolo(id);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
		
	}
}
