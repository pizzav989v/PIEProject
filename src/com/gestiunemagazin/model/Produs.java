package com.gestiunemagazin.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



public class Produs {
	private int idProdus;
	private String denumire;
	private double pret;
	private int stoc;
	private String descriere;
	
	

	public int getIdProdus() {
		return idProdus;
	}



	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}



	public String getDenumire() {
		return denumire;
	}



	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}



	public double getPret() {
		return pret;
	}



	public void setPret(double pret) {
		this.pret = pret;
	}



	public int getStoc() {
		return stoc;
	}



	public void setStoc(int stoc) {
		this.stoc = stoc;
	}



	public String getDescriere() {
		return descriere;
	}



	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}



	/**
	 * metoda toString() pentru obiectul Produs
	 * metoda ajutatoare in logarea campurilor obiectului
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
