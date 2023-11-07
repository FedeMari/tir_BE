package com.tir.ocinio.repository.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tir.ocinio.entity.Assegnazione;
import com.tir.ocinio.repository.mapper.AssegnazioneRowMapper;
import com.tir.ocinio.repository.query.AssegnazioneQuery;

@Repository
public class AssegnazioneDAO implements DAO<Assegnazione>{

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public Assegnazione getById(Long id) {
		
		String query = String.format(AssegnazioneQuery.oneAssegnazione, id);
		var assegnazione = template.queryForObject(query, new AssegnazioneRowMapper());
		return assegnazione;
	}
	
	public List<Assegnazione> getAllAssById_Dip(Long id_dip) {
		String query = String.format(AssegnazioneQuery.AllAssegnazioniByDip, id_dip);
		var assegnazione = template.query(query, new AssegnazioneRowMapper());
		return assegnazione;
	}
	
	public List<Assegnazione> getAllAssById_Com(Long id_com) {
		String query = String.format(AssegnazioneQuery.AllAssegnazioniByCom, id_com);
		var assegnazione = template.query(query, new AssegnazioneRowMapper());
		return assegnazione;
	}

	@Override
	public List<Assegnazione> getAll() {
		String query = AssegnazioneQuery.allAssegnazioni;
		var assegnazioni = template.query(query, new AssegnazioneRowMapper());
		return assegnazioni;
	}
	
	public List<Assegnazione> getAllAttive() {
		String query = AssegnazioneQuery.allAssegnazioniAttive;
		var assegnazioni = template.query(query, new AssegnazioneRowMapper());
		return assegnazioni;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Assegnazione insert(Assegnazione t) {

		Assegnazione newAssegnazione = null;

		var function = new SimpleJdbcCall(template).withCatalogName("GRUPPO_3")
				.withFunctionName("F_INSERT_ASSEGNAZIONI");

		var newId = function.executeFunction(BigDecimal.class, t.getDipendente().getId(), t.getCommessa().getId(), t.getCompetenza()).longValue();

		newAssegnazione = getById(newId);

		return newAssegnazione;
	}

	@Override
	public void delete(Long id) {
		
		var procedure = new SimpleJdbcCall(template).withCatalogName("GRUPPO_3")
						.withProcedureName("P_DELETE_ASSEGNAZIONI");
		
		procedure.execute(id);
						
	}

	@Override
	public Assegnazione update(Assegnazione t) {
		
		var procedure = new SimpleJdbcCall(template).withCatalogName("GRUPPO_3")
				.withProcedureName("P_UPDATE_ASSEGNAZIONI");
		
		procedure.execute(t.getCompetenza(), t.getId());
		
		
		return getById(t.getId());
	}

}
