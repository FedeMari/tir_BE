package com.tir.ocinio.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tir.ocinio.entity.Consuntivo;
import com.tir.ocinio.entity.Dipendente;
import com.tir.ocinio.repository.dao.ConsuntivoDAO;
import com.tir.ocinio.repository.dao.DAO;

@Service
public class ConsuntivoService {

	@Autowired
	private DAO<Consuntivo> conDao;
	
	@Autowired
	private DAO<Dipendente> dipDao;
	
	public Consuntivo getConsuntivoById(long id) {
		
		var consuntivo = conDao.getById(id);
		
		consuntivo.setDipendente(dipDao.getById(consuntivo.getDipendente().getId()));
		
		return consuntivo;
	}
	
	
	public List<Consuntivo> getAllConsuntivi(){
		//var dip = ((Dipendente)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		var consuntivi = conDao.getAll();
		
		for(var con : consuntivi) {
			
			con.setDipendente(dipDao.getById(con.getDipendente().getId()));
			
		}
		
		return consuntivi;
	}
	
	public Integer getCountConsuntivi() {
		var counterConsuntivi = conDao.count();
		return counterConsuntivi;
	}
	
	public Consuntivo insertConsuntivo(Consuntivo con) {
		var dip = ((Dipendente)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		con.setDipendente(dip);
		return conDao.insert(con);
	}
	
	public Consuntivo updateConsuntivo(Consuntivo con) {
	
		
		return conDao.update(con);
	}
	
	public void deleteConsuntivo(Long id) {
		conDao.delete(id);
	}
	
	//sezione dipendente 
	public List<Consuntivo> GetMyConsuntivi(){
		var dip = ((Dipendente)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		var consuntivi = ((ConsuntivoDAO)conDao).getByDipendente(dip.getId());
		
		for (Consuntivo consuntivo : consuntivi) {
			consuntivo.setDipendente(dip);
		}
		
		return consuntivi;
	}
	
}
