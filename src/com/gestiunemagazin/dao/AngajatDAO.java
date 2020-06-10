package com.gestiunemagazin.dao;

import java.util.ArrayList;

import com.gestiunemagazin.model.Angajat;

public interface AngajatDAO {

	public void adaugaAngajat(Angajat angajat);

	public void stergeAngajat(int idAngajat);

	public void updateAngajat(Angajat angajat);

	public ArrayList<Angajat> getAllAngajati();

	public ArrayList<Angajat> getAllAngajatiByNumeOrPren(String nume);

	public ArrayList<Angajat> getAllAngajatiBetweenDate();

	public Angajat getAngajatById(int idAngajat);

	public void verificaAngajat(Angajat angajat);

}
