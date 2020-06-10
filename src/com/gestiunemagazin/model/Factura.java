package com.gestiunemagazin.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



public class Factura {

	private int idFactura;
	private double valoare;
	private int idAngajat;
	private int idClient;
	private int idSejur;
	private Date data;

	
	public int getIdFactura() {
		return idFactura;
	}

	
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	
	public double getValoare() {
		return valoare;
	}

	
	public void setValoare(double valoare) {
		this.valoare = valoare;
	}

	
	public int getIdAngajat() {
		return idAngajat;
	}

	
	public void setIdAngajat(int idAngajat) {
		this.idAngajat = idAngajat;
	}

	
	public int getIdClient() {
		return idClient;
	}

	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	
	public int getIdSejur() {
		return idSejur;
	}

	
	public void setIdSejur(int idSejur) {
		this.idSejur = idSejur;
	}

	
	public Date getData() {
		return data;
	}

	
	public void setData(Date data) {
		this.data = data;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
