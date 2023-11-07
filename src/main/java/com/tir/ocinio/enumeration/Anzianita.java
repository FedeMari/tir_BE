package com.tir.ocinio.enumeration;

public enum Anzianita {

	JUNIOR("Junior"), MIDDLE("Middle"), SENIOR("Senior"), HR("HR");
	
	public final String nome;
	
	private Anzianita(String nome) {
		this.nome = nome;
	}
	
}
