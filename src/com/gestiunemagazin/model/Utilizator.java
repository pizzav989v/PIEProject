package com.gestiunemagazin.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



public class Utilizator {

	private int idUtilizator;
	private String email;
	private String parola;
	private int tipUtilizator;

	
	public int getIdUtilizator() {
		return idUtilizator;
	}

	
	public void setIdUtilizator(int idUtilizator) {
		this.idUtilizator = idUtilizator;
	}

	
	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getParola() {
		return parola;
	}

	
	public void setParola(String parola) {
		this.parola = parola;
	}

	
	public int getTipUtilizator() {
		return tipUtilizator;
	}

	
	public void setTipUtilizator(int tipUtilizator) {
		this.tipUtilizator = tipUtilizator;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
