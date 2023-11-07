package com.tir.ocinio.repository.query;

public class CommessaQuery {

	public static String allCommesse = "select c.id com_id, c.tipo com_tipo, c.durata com_durata, "
			+ "c.descrizione com_descrizione, c.importo_contratto com_importo_contratto, c.data_inizio com_data_inizio, c.data_fine com_data_fine, "
			+ "c.attivo com_attivo, cl.id cli_id, cl.nome cli_nome, "
			+ "cl.p_iva cli_p_iva, cl.telefono cli_telefono " + "from Commesse c, Clienti cl " + "where c.id_cliente = cl.id(+) ";

	public static String oneCommessa = allCommesse + "and c.id = %d";


	public static String countCommessa = "select count(*) from  Commesse";

}
