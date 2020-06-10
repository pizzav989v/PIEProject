package com.gestiunemagazin.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



public class Angajat {

	private int idAngajat;
	private String nume;
	private String prenume;
	private String cnp;
	private String telefon;
	private double salariu;
	private Date dataAngajare;
	private String functie;
	private int idDepartament;
	private String numeDepartament;
	private String email;

	
	public int getIdAngajat() {
		return idAngajat;
	}

	
	public void setIdAngajat(int idAngajat) {
		this.idAngajat = idAngajat;
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

	
	public String getTelefon() {
		return telefon;
	}

	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	
	public double getSalariu() {
		return salariu;
	}

	
	public void setSalariu(double salariu) {
		this.salariu = salariu;
	}

	
	public Date getDataAngajare() {
		return dataAngajare;
	}

	
	public void setDataAngajare(Date dataAngajare) {
		this.dataAngajare = dataAngajare;
	}

	
	public String getFunctie() {
		return functie;
	}

	
	public void setFunctie(String functie) {
		this.functie = functie;
	}

	
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
