package com.tir.ocinio.exception;

@SuppressWarnings("serial")
public class JsonSerializerFormatException extends RuntimeException{

	public JsonSerializerFormatException(String string) {
		super(string);
	}

}