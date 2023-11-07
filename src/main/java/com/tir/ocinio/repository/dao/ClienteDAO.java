package com.tir.ocinio.repository.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tir.ocinio.entity.Cliente;
import com.tir.ocinio.repository.mapper.ClienteRowMapper;
import com.tir.ocinio.repository.query.ClienteQuery;

@Repository
public class ClienteDAO implements DAO<Cliente>{
	
	@Autowired 
	private JdbcTemplate template;
	
	@Override
	public Cliente getById(Long id) {
		String query = String.format(ClienteQuery.oneCliente, id);   
		var cliente = template.queryForObject(query, new ClienteRowMapper());		
		return cliente;
	}

	@Override
	public List<Cliente> getAll() {
		String query = ClienteQuery.allClienti;
		var clienti = template.query(query, new ClienteRowMapper());
		return clienti;
	}

	@Override
	public int count() {
		String query = ClienteQuery.countClienti;
		var counter = template.queryForObject(query, Integer.class);
		return counter;
	}

	@Override
	public Cliente insert(Cliente t) {
		Cliente newCliente = null;
		
		var function = new SimpleJdbcCall(template).
							withCatalogName("GRUPPO_1").
							withFunctionName("F_INSERISCI_CLIENTE");
		
		var newId = function.executeFunction(BigDecimal.class, t.getNome(), t.getPartitaIva(), t.getTelefono()).longValue(); 
		//metodo della classe BidDecimal che ritorna il corrispettivo valore messo in una variabile di tipo
		
		newCliente = getById(newId);
		
		return newCliente;
	}

	@Override
	public void delete(Long id) {
		
		var procedure = new SimpleJdbcCall(template).
							withCatalogName("GRUPPO_1").
							withProcedureName("P_DELETE_CLIENTE");
		
		procedure.execute(id);
	}

	@Override
	public Cliente update(Cliente t) {
		
		var procedure = new SimpleJdbcCall(template).
				withCatalogName("GRUPPO_1").
				withProcedureName("P_UPDATE_CLIENTE");

		procedure.execute(t.getId(), t.getTelefono());

		return getById(t.getId());
	}
	
	

}
