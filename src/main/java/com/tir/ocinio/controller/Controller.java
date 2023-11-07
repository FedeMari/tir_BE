package com.tir.ocinio.controller;

import com.tir.ocinio.util.JsonSerializer;

public abstract class Controller {

	protected JsonSerializer serializer = new JsonSerializer();
	
	protected String format;
	
}
