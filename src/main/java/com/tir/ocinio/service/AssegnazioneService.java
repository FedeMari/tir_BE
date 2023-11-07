package com.tir.ocinio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tir.ocinio.entity.Assegnazione;
import com.tir.ocinio.repository.dao.AssegnazioneDAO;


@Service
public class AssegnazioneService {

	
	@Autowired
	private AssegnazioneDAO assDao;
	
	public Assegnazione getAsssegnazioneById(Long id) {
		return assDao.getById(id);
	}
	
	public List<Assegnazione> getAllAssegnazioni(){
		return assDao.getAll();
	}
	
	public List<Assegnazione> getAllAssegnazioniAttive(){
		return assDao.getAllAttive();
	}
	
	public List<Assegnazione> getAssegnazioneByIdDipendente(Long id_dip) {
		return assDao.getAllAssById_Dip(id_dip);
	}
	
	public List<Assegnazione> getAssegnazioneByIdCommessa(Long id_com) {
		return assDao.getAllAssById_Com(id_com);
	}
	
	public Assegnazione inserisciAssegnazione(Assegnazione a) {
		return assDao.insert(a);
	}
	
	public Assegnazione modificaAssegnazione(Assegnazione a) {
		return assDao.update(a);
	}
	
	public void eliminaAssegnazione(Long id) {
		assDao.delete(id);
	}
}
