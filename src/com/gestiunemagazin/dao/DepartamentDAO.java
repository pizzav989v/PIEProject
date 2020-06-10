package com.gestiunemagazin.dao;

import java.util.ArrayList;

import com.gestiunemagazin.model.Departament;


public interface DepartamentDAO {
	
	public void adaugaDepartament(Departament departament);

	
	public void stergeDepartament(int idDepartament);

	
	public void updateDepartament(Departament departament);

	
	public ArrayList<Departament> getAllDepartamente();

	
	public Departament getDepartamentById(int idDepartament);

	
	public void verificaDepartament(Departament departament);
}
