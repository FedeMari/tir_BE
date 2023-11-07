package com.tir.ocinio.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tir.ocinio.entity.Cliente;

public class ClienteRowMapper implements RowMapper<Cliente>{

	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		var cliente = new Cliente();
		
		cliente.setId(rs.getLong("cli_id"));
		cliente.setNome(rs.getString("cli_nome"));
		cliente.setPartitaIva(rs.getString("cli_p_iva"));
		cliente.setTelefono(rs.getString("cli_telefono"));
		
		
		return cliente;
	}

}
