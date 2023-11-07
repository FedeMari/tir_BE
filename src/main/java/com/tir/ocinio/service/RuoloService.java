package com.tir.ocinio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tir.ocinio.entity.Ruolo;
import com.tir.ocinio.repository.dao.DAO;

@Service
public class RuoloService {
	@Autowired
	private DAO<Ruolo> ruoDao;
	
	public Ruolo getRuoloById(long id){
		
		var ruolo = ruoDao.getById(id);
		
		return ruolo;		
	}
	
	public List<Ruolo> getAllRuoli(){
		
		var ruoli = ruoDao.getAll();
		
		return ruoli;
	}
	
	public Ruolo insertRuolo (Ruolo ruo) {
		
		return ruoDao.insert(ruo);
		
	}
	
	public Ruolo updateRuolo (Ruolo ruo) {
		
		return ruoDao.update(ruo);
		
	}
	
	public void deleteRuolo (Long id) {
		
		ruoDao.delete(id);
		
	}
}