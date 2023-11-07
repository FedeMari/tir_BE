package com.tir.ocinio.repository.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tir.ocinio.entity.Ruolo;
import com.tir.ocinio.repository.mapper.RuoloRowMapper;
import com.tir.ocinio.repository.query.RuoloQuery;

@Repository
public class RuoloDAO implements DAO<Ruolo>{


	@Autowired
	private JdbcTemplate template;

	@Override
	public Ruolo getById(Long id) {

		String query = String.format(RuoloQuery.oneRuolo, id);

		var ruolo = template.queryForObject(query, new RuoloRowMapper()); 

		return ruolo;
	}

	@Override
	public List<Ruolo> getAll() {

		String query = RuoloQuery.allRuoli;

		var ruoli =  template.query(query, new RuoloRowMapper());
		return ruoli;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Ruolo insert(Ruolo t) {

		var function = new SimpleJdbcCall(template).
				withCatalogName("GRUPPO_2").
				withFunctionName("F_INSERT_RUOLI");

		var newId = function.executeFunction(BigDecimal.class, t.getCompenso(), t.getAnzianita()).longValue();

		return getById(newId);
	}

	@Override
	public void delete(Long id) {

		var procedure = new SimpleJdbcCall(template).
				withCatalogName("GRUPPO_2").
				withProcedureName("P_DELETE_RUOLI");

		procedure.execute(id);

	}

	@Override
	public Ruolo update(Ruolo t) {
		
		var procedure = new SimpleJdbcCall(template).
				withCatalogName("GRUPPO_2").
				withProcedureName("P_UPDATE_RUOLI");

		procedure.execute(t.getId(), t.getCompenso(), t.getAnzianita());
		
		return getById(t.getId());
	}



}
