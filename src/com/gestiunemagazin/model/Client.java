package com.gestiunemagazin.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



public class Client {

	private int idClient;
	private String nume;
	private String prenume;
	private String cnp;
	private String adresa;
	private String telefon;
	private String email;

	
	public int getIdClient() {
		return idClient;
	}

	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	
	public String getNume() {
		return nume;
	}

	
	public void setNume(String nume) {
		this.nume = nume;
	}

	
	public String getPrenume() {
		return prenume;
	}

	
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	
	public String getCnp() {
		return cnp;
	}

	
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	
	public String getAdresa() {
		return adresa;
	}

	
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	
	public String getTelefon() {
		return telefon;
	}

	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	
	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
