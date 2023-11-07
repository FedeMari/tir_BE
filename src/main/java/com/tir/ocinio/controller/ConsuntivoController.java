package com.tir.ocinio.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.tir.ocinio.entity.Consuntivo;
import com.tir.ocinio.service.ConsuntivoService;

@RestController
@RequestMapping("api/consuntivo")
public class ConsuntivoController extends Controller{
	
	
	@Autowired
	private ConsuntivoService conService;

	
	public ConsuntivoController() {
		this.format = "{id,orarioInizio,orarioFine,tipologia,dipendente:{id,nome}}";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HashMap<String, Object>> getConsuntivoById(@PathVariable(value = "id") Long id) {
		var consuntivo = conService.getConsuntivoById(id);	
		var conMap = serializer.serialize(format, consuntivo);
		return ResponseEntity.ok(conMap);
	}		
	
	@GetMapping("/all")
	public ResponseEntity<List<HashMap<String, Object>>> getAllConsuntivi(){
		List<Consuntivo> consuntivi = conService.getAllConsuntivi();
		var allMap = serializer.serializeAll(format, consuntivi);
		return ResponseEntity.ok(allMap);
	}
	@GetMapping("/my")
	public ResponseEntity<List<HashMap<String, Object>>> getConsuntivoByLoggedUser(){
	
		List<Consuntivo> consuntivi = conService.GetMyConsuntivi();
		var allMap = serializer.serializeAll(format, consuntivi);
		return ResponseEntity.ok(allMap);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Integer> getCountConsuntivi(){
		var counterConsuntivi = conService.getCountConsuntivi();
		return new ResponseEntity<>(counterConsuntivi, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<HashMap<String, Object>> insertConsuntivo (@RequestBody Consuntivo con) {
		con = conService.insertConsuntivo(con);
		var insMap = serializer.serialize(format, con);
		return ResponseEntity.ok(insMap);	}
	
	@PutMapping("")
	public ResponseEntity<HashMap<String, Object>> updateConsuntivo (@RequestBody Consuntivo con){
		con = conService.updateConsuntivo(con);
		var uptMap = serializer.serialize(format, con);
		return ResponseEntity.ok(uptMap);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteConsuntivo (@PathVariable(value = "id") Long id){
		conService.deleteConsuntivo(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
