package com.tir.ocinio.repository.query;

public class TaskQuery {
	
	public static String allTask = "SELECT t.id tas_id, t.id_assegnazione tas_id_assegnazione, "
			+ "t.descrizione tas_descrizione, t.nome tas_nome, "
			+ "t.data_assegnazione tas_data_assegnazione, t.data_consegna tas_data_consegna"
			+" FROM Task t";

			public static String oneTask = allTask + " WHERE t.id = %d";

			public static String countTask = "SELECT count(*) FROM Task";

}
