package com.gestiunemagazin.dao;

import java.util.ArrayList;

import com.gestiunemagazin.model.Utilizator;



public interface UtilizatorDAO {
	
	public void adaugaUtilizator(Utilizator angajat);

	
	public void stergeUtilizator(int idUtilizator);

	
	public void updateUtilizator(Utilizator angajat);

	
	public ArrayList<Utilizator> getAllUtilizatori();
	
	
	public ArrayList<Utilizator> getAllUtilizatoriByEmail(String email);

	
	public Utilizator getUtilizatorById(int idUtilizator);

	
	public void verificaUtilizator(Utilizator utilizator);

	
	public Utilizator getUtilizatorByEmailAndPass(String email, String parola);
	
	
	public Utilizator getUtilizatorByEmail(String email);

}
