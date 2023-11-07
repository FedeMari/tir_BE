package com.tir.ocinio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tir.ocinio.entity.Commessa;
import com.tir.ocinio.repository.dao.DAO;

@Service
public class CommessaService {

	@Autowired
	private DAO<Commessa> comDao;

	public Commessa getCommessaById(long id) {

		var commessa = comDao.getById(id);

		return commessa;
	}

	public List<Commessa> getAllCommesse() {

		var commesse = comDao.getAll();

		return commesse;
	}

	public Integer getCountCommessa() {

		var counterCommessa = comDao.count();

		return counterCommessa;

	}

	public Commessa insertCommessa(Commessa com) {
		return comDao.insert(com);
	}
	
	public Commessa updateCommessa(Commessa com) {
		return comDao.update(com);
	}
	public void deleteCommessa(Long id) {
		comDao.delete(id);
	}
}
