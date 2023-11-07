package com.tir.ocinio.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tir.ocinio.entity.Cliente;
import com.tir.ocinio.service.ClienteService;

@RestController
@RequestMapping("api/cliente")
public class ClienteController extends Controller{
	
	@Autowired
	private ClienteService cliService;
	
	
	public ClienteController() {
		this.format = "{id, nome, partitaIva, telefono}";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable(value = "id") Long id){
		Cliente cliente = cliService.getClienteById(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<HashMap<String, Object>>> getAllClienti(){
		List<Cliente> clienti = cliService.getAllClienti();
		var cliList = serializer.serializeAll(format, clienti);
		return new ResponseEntity<>(cliList, HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Integer> getCountClienti(){
		var counterClienti = cliService.getCountClienti();
		return new ResponseEntity<>(counterClienti, HttpStatus.OK);
	}
	
	
	@PostMapping("")
	public ResponseEntity<HashMap<String, Object>> insertCliente(@RequestBody Cliente cli){
		cli = cliService.insertCliente(cli);
		var cliMap = serializer.serialize(format, cli);
		return new ResponseEntity<>(cliMap, HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<HashMap<String, Object>> updateCliente(@RequestBody Cliente cli){
		cli = cliService.updateCliente(cli);
		var cliMap = serializer.serialize(format, cli);
		return new ResponseEntity<>(cliMap, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HashMap<String, Object>> deleteCliente(@PathVariable(value = "id") Long id){
		cliService.deleteCliente(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
}
