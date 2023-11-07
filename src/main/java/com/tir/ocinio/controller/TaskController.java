package com.tir.ocinio.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tir.ocinio.entity.Task;
import com.tir.ocinio.service.TaskService;

@RestController
@RequestMapping("api/task")
public class TaskController extends Controller{
	@Autowired
	private TaskService tasService;
	
	public TaskController() {
		format = "{id, descrizione, nome, dataAssegnazione, dataConsegna, assegnazione}";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long id) {
		Task task = tasService.getTaskById(id);
		return new ResponseEntity<>(task, HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Task>> getAllTask(){
		List<Task> task = tasService.getAllTask();
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Integer> getCountTask(){
		var counterTask = tasService.getCountTask();
		return new ResponseEntity<>(counterTask, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<HashMap<String, Object>> inserisciTask(@RequestBody Task t){
		t = tasService.insertTask(t);
		var tasMap = serializer.serialize(format, t);
		return ResponseEntity.ok(tasMap);
	}
	
	@PutMapping("")
	public ResponseEntity<HashMap<String, Object>> modificaTask(@RequestBody Task t){
		t = tasService.updateTask(t);
		var tasMap = serializer.serialize(format, t);
		return ResponseEntity.ok(tasMap); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminaTask(@PathVariable("id")Long id){
		tasService.deleteTask(id);
		return ResponseEntity.ok(null);
	}
	
	
}
