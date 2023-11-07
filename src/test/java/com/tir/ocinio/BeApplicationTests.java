package com.tir.ocinio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tir.ocinio.entity.Dipendente;
import com.tir.ocinio.repository.dao.DAO;

@SpringBootTest
class BeApplicationTests {

	@Autowired 
	private DAO<Dipendente> dao;
	
	@Test
	void contextLoads() {
		
		var federico = dao.getById(1L);
		
		System.out.println(federico);
		
	}
	
	@Test
	void testInsertDipendente() {
		
		var alessandro = new Dipendente();
		
		
		alessandro.setNome("Alessandro");
		alessandro.setCognome("Marchionne");
		alessandro.setCf("MRCLSN95P17H501N");
		alessandro.setEmail("alessandro@gmail.com");
		alessandro.setPassword("1234");
		alessandro.setTelefono("3929839282");
		
		alessandro = dao.insert(alessandro);
		
		System.out.println(alessandro);
		
		
	}
	
	@Test
	void testDeleteDipendente() {
		
		dao.delete(24L);
		
	}

	

}
