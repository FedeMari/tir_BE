package com.tir.ocinio.repository.query;

public class RuoloQuery {
	
	public static String allRuoli = "select r.id ruo_id, r.compenso ruo_compenso, r.anzianita ruo_anzianita"
									+ " from Ruoli r";
	
	public static String oneRuolo = allRuoli + " where r.id = %d";
	

}
