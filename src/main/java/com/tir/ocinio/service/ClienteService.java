package com.tir.ocinio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tir.ocinio.entity.Cliente;
import com.tir.ocinio.repository.dao.DAO;

@Service
public class ClienteService {
	
	@Autowired
	private DAO<Cliente> cliDao;
	
	public Cliente getClienteById(long id){
		
		var cliente = cliDao.getById(id);
		
		return cliente;		
	}
	
	public List<Cliente> getAllClienti(){
		
		var clienti = cliDao.getAll();
		
		return clienti;
	}
	
	public Integer getCountClienti() {
		
		var counterClienti = cliDao.count();
		
		return counterClienti;
		
	}	
	
	public Cliente insertCliente(Cliente cli) {
		return cliDao.insert(cli);
	}
	
	public Cliente updateCliente(Cliente cli) {
		return cliDao.update(cli);
	}
	
	public void deleteCliente(Long id) {
		cliDao.delete(id);
	}	

}
