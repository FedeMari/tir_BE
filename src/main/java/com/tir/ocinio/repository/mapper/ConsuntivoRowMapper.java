package com.tir.ocinio.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tir.ocinio.entity.Consuntivo;
import com.tir.ocinio.entity.Dipendente;

public class ConsuntivoRowMapper implements RowMapper<Consuntivo>{
	
	private DipendenteRowMapper dipendenteMapper = new DipendenteRowMapper();

	@Override
	public Consuntivo mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		var consuntivo =  new Consuntivo();
		
		var temp_inizio= rs.getTimestamp("con_orario_inizio");
		var temp_fine= rs.getTimestamp("con_orario_fine");
		
		consuntivo.setId(rs.getLong("con_id"));
		consuntivo.setOrarioInizio(temp_inizio.toLocalDateTime());
		if(temp_fine != null) {
			consuntivo.setOrarioFine(temp_fine.toLocalDateTime());
		}
		consuntivo.setTipologia(rs.getString("con_tipologia"));

		var dipendente = new Dipendente(rs.getLong("dip_id"));
		consuntivo.setDipendente(dipendente);
		
		return consuntivo;
	}
	
	

}
