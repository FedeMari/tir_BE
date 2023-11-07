package com.tir.ocinio.repository.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tir.ocinio.entity.Task;
import com.tir.ocinio.repository.mapper.TaskRowMapper;
import com.tir.ocinio.repository.query.TaskQuery;

@Repository
public class TaskDAO implements DAO<Task> {
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public Task getById(Long id) {
		String query = String.format(TaskQuery.oneTask, id);
		var task = template.queryForObject(query, new TaskRowMapper());
		return task;
	}

	@Override
	public List<Task> getAll() {
		String query = TaskQuery.allTask;
		var task = template.query(query, new TaskRowMapper());
		return task;
	}

	@Override
	public int count() {
		String query = TaskQuery.countTask;
		var counter = template.queryForObject(query, Integer.class);
		return counter;
	
	}

	
	@Override
	public Task insert(Task t) {

		Task newTask = new Task();
		
		var function = new SimpleJdbcCall(template).withCatalogName("GRUPPO_1")
				.withFunctionName("F_INSERT_TASK");
		
		var newID = function.executeFunction(BigDecimal.class, t.getAssegnazione().getId(), t.getNome(), t.getDescrizione(), t.getDurata()).longValue();
		
		newTask = getById(newID);
		
		return newTask;
	}
	

	@Override
	public void delete(Long id) {
		
		var procedure = new SimpleJdbcCall(template)
								.withCatalogName("GRUPPO_1")
								.withProcedureName("P_DELETE_TASK");
		procedure.execute(id);
	}

	@Override
	public Task update(Task t) {
		
		var procedure = new SimpleJdbcCall(template).withCatalogName("GRUPPO_1")
				.withProcedureName("P_UPDATE_TASK");
		
		procedure.execute(t.getId(), t.getNome(), t.getDescrizione(), t.getDataConsegna());
		
		return getById(t.getId());
	}
	
	

}
