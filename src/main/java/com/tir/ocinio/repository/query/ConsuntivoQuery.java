package com.tir.ocinio.repository.query;

public class ConsuntivoQuery {
	
	public static String allConsuntivi = "select c.id con_id, c.orario_inizio con_orario_inizio, c.orario_fine con_orario_fine, "
							+ "c.tipologia con_tipologia, d.id dip_id "
							+ "from Consuntivi c, Dipendenti d "
							+ "where c.id_dipendente = d.id";
	  

	public static String oneConsuntivo = allConsuntivi + " and c.id = %d";
	
	public static String countConsuntivi = "select count(*) from consuntivi";
	
	public static String myConsuntivi = "select c.id con_id, c.orario_inizio con_orario_inizio, c.orario_fine con_orario_fine, "
							+ "c.tipologia con_tipologia, c.id_dipendente dip_id "
							+ "from Consuntivi c "
							+ "where c.id_dipendente = %d";
	  
	
}