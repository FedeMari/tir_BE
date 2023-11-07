package com.tir.ocinio.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tir.ocinio.entity.Ruolo;
import com.tir.ocinio.enumeration.Anzianita;

public class RuoloRowMapper implements RowMapper<Ruolo>{

	@Override
	public Ruolo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		var ruolo = new Ruolo();
		
		ruolo.setId(rs.getLong("ruo_id"));
		ruolo.setCompenso(rs.getFloat("ruo_compenso"));
		ruolo.setAnzianita(Anzianita.valueOf(rs.getString("ruo_anzianita").toUpperCase()));
		
		return ruolo;
	}

}
