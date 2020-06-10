package com.gestiunemagazin.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



public class Departament {

	private int idDepartament;
	private String numeDepartament;

	
	public int getIdDepartament() {
		return idDepartament;
	}

	
	public void setIdDepartament(int idDepartament) {
		this.idDepartament = idDepartament;
	}

	
	public String getNumeDepartament() {
		return numeDepartament;
	}

	
	public void setNumeDepartament(String numeDepartament) {
		this.numeDepartament = numeDepartament;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
