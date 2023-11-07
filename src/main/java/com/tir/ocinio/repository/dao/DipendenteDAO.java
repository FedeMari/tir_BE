package com.tir.ocinio.repository.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tir.ocinio.entity.Dipendente;
import com.tir.ocinio.repository.mapper.DipendenteRowMapper;
import com.tir.ocinio.repository.query.DipendenteQuery;

@Repository
public class DipendenteDAO implements DAO<Dipendente>{

	@Autowired 
	private JdbcTemplate template;
	
	@Override
	public Dipendente getById(Long id) {
		
		String query = String.format(DipendenteQuery.oneDipendente, id);
		var dipendente = template.queryForObject(query, new DipendenteRowMapper());
		return dipendente;
	}

	@Override
	public List<Dipendente> getAll() {
		String query = DipendenteQuery.allDipendentiOrdered;
		var dipendenti = template.query(query, new DipendenteRowMapper());
		return dipendenti;
	}

	@Override
	public int count() {
		String query = DipendenteQuery.countDipendenti;
		var counter = template.queryForObject(query, Integer.class);
		return counter;
	}

	@Override
	public Dipendente insert(Dipendente t) {
		
		var function = new SimpleJdbcCall(template).
							withCatalogName("GRUPPO_1").
							withFunctionName("F_INSERISCI_DIPENDENTE");
		
		var newId = function.executeFunction(BigDecimal.class, t.getNome(), t.getCognome(), t.getCf(), 
												t.getEmail(), t.getPassword(), t.getTelefono()).longValue();
		
		
		return getById(newId);
		
	}

	@Override
	public void delete(Long id) {
		
		
		var procedure = new SimpleJdbcCall(template).
							withCatalogName("GRUPPO_1").
							withProcedureName("P_DELETE_DIPENDENTE");
		
		procedure.execute(id);
		
	}

	@Override
	public Dipendente update(Dipendente t) {

		var procedure = new SimpleJdbcCall(template).
							withCatalogName("GRUPPO_1").
							withProcedureName("P_UPDATE_DIPENDENTE");
		
		procedure.execute(t.getId(), t.getNome(), t.getCognome(), t.getCf(),t.getRuolo().getId(), t.getPassword(), t.getTelefono());
		
		return getById(t.getId());
		
	}
	
	public String getToken(Long id) {
		String query = String.format(DipendenteQuery.tokenRegistrazione, id);
		var token = template.queryForObject(query, String.class);
		return token;
	}
	
	public void confirmToken(String token) {
		
		var procedure = new SimpleJdbcCall(template).
				withCatalogName("GRUPPO_1").
				withProcedureName("P_CONFIRM_TOKEN");
		
		procedure.execute(token);		
	}
	
	public Dipendente getByEmail (String email) {
		
		String query = String.format(DipendenteQuery.oneDipendenteByEmail, email);
		var dipendente = template.queryForObject(query, new DipendenteRowMapper());
		return dipendente;
		
	}
	
	
}
