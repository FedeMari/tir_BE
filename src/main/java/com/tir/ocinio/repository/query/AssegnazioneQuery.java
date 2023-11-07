package com.tir.ocinio.repository.query;

public class AssegnazioneQuery {
	
	public static String allAssegnazioni = "SELECT a.id_dipendente ass_id_dipendente, a.id_commessa ass_id_commessa, a.competenza ass_competenza, a.attivo ass_attivo, a.id ass_id, d.nome dip_nome, d.cognome dip_cognome FROM ASSEGNAZIONI a, DIPENDENTI d WHERE a.id_dipendente = d.id ";
	
	public static String allAssegnazioniAttive = allAssegnazioni + " AND a.attivo = 1";
	
	public static String AllAssegnazioniByDip = allAssegnazioni + " AND a.id_dipendente = %d";

	public static String AllAssegnazioniByCom = allAssegnazioni + " AND a.id_commessa = %d";
	
	public static String oneAssegnazione = allAssegnazioni + " AND a.id = %d";
	
}
