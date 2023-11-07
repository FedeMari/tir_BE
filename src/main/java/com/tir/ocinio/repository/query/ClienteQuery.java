package com.tir.ocinio.repository.query;

public class ClienteQuery {
	
	public static String allClienti = "select c.id cli_id, c.nome cli_nome, c.p_iva cli_p_iva, c.telefono cli_telefono " 
									+ " from clienti c";
	
	public static String oneCliente = allClienti + " where c.id = %d";
	
	public static String countClienti = "select count(*) from clienti";	

}
