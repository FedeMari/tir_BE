package com.tir.ocinio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tir.ocinio.entity.Task;
import com.tir.ocinio.repository.dao.DAO;

@Service
public class TaskService {
	@Autowired
	private DAO<Task> tasDao;
	
	public Task getTaskById(long id){
		
		var task = tasDao.getById(id);
		
		return task;		
	}
	
	public List<Task> getAllTask(){
		
		var task = tasDao.getAll();
		
		return task;
	}
	
	public Integer getCountTask() {
		
		var counterTask = tasDao.count();
		
		return counterTask;
		
	}
	
	public Task insertTask(Task t) {
		var task = tasDao.insert(t);
		return task;
	}
	
	public Task updateTask(Task t) {
		var task = tasDao.update(t);
		return task;
	}
	
	public void deleteTask(Long id_task) {
		tasDao.delete(id_task);
	}
}
