package com.tir.ocinio.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tir.ocinio.entity.Commessa;

public class CommessaRowMapper implements RowMapper<Commessa>{

	private ClienteRowMapper clienteMapper = new ClienteRowMapper();
	@Override
	public Commessa mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Commessa commessa = new Commessa();
		
		commessa.setId(rs.getLong("com_id"));
		commessa.setTipo(rs.getString("com_tipo"));
		commessa.setDurata(rs.getLong("com_durata"));
		commessa.setDescrizione(rs.getString("com_descrizione"));
		commessa.setImportoContratto(rs.getFloat("com_importo_contratto"));
		commessa.setDataInizio(rs.getDate("com_data_inizio"));
		commessa.setDataFine(rs.getDate("com_data_fine"));
		commessa.setAttivo(rs.getInt("com_attivo") == 1);
		commessa.setCliente(clienteMapper.mapRow(rs, rowNum));
		return commessa;
	}

}
