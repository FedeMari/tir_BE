package com.tir.ocinio.repository.query;

public class DipendenteQuery {

	public static String allDipendenti = "select d.id dip_id, d.nome dip_nome, d.cognome dip_cognome, "
									   + "d.cf dip_cf, d.email dip_email, d.password dip_password, d.telefono dip_telefono, "
									   + "d.registrato dip_registrato, d.attivo dip_attivo, r.id ruo_id, r.anzianita ruo_anzianita, "
									   + "r.compenso ruo_compenso "
									   + "from Dipendenti d, Ruoli r  "
									   + "where d.id_ruolo = r.id(+)";
	
	public static String allDipendentiOrdered = allDipendenti + " order by d.id";
	
	public static String oneDipendente = allDipendenti + " and d.id = '%s'";
	
	public static String countDipendenti = "select count(*) from dipendenti";
	
	public static String tokenRegistrazione = "select token from token_registrazione "
									        + "where id_dipendente = %d";
	
	public static String oneDipendenteByEmail = "select d.id dip_id, d.nome dip_nome, d.cognome dip_cognome, "
			   + "d.cf dip_cf, d.email dip_email, d.password dip_password, d.telefono dip_telefono, "
			   + "d.registrato dip_registrato, d.attivo dip_attivo, r.id ruo_id, r.anzianita ruo_anzianita, "
			   + "r.compenso ruo_compenso "
			   + "from Dipendenti d, Ruoli r "
			   + "where d.id_ruolo = r.id(+) AND d.email = '%s' ";}
