package com.tir.ocinio.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tir.ocinio.entity.Dipendente;

public class DipendenteRowMapper implements RowMapper<Dipendente>{

	private RuoloRowMapper ruoloMapper = new RuoloRowMapper();
	
	@Override
	public Dipendente mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		var dipendente = new Dipendente();
		
		dipendente.setId(rs.getLong("dip_id"));
		dipendente.setNome(rs.getString("dip_nome"));
		dipendente.setCognome(rs.getString("dip_cognome"));
		dipendente.setCf(rs.getString("dip_cf"));
		dipendente.setEmail(rs.getString("dip_email"));
		dipendente.setPassword(rs.getString("dip_password"));
		dipendente.setTelefono(rs.getString("dip_telefono"));
		
		dipendente.setRuolo(ruoloMapper.mapRow(rs, rowNum));
		
		dipendente.setRegistrato(rs.getInt("dip_registrato") == 1);
		dipendente.setAttivo(rs.getInt("dip_attivo") == 1);
		
		return dipendente;
		
	}

}
