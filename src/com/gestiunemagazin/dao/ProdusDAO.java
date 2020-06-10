package com.gestiunemagazin.dao;

import java.util.ArrayList;

import com.gestiunemagazin.model.Produs;



public interface ProdusDAO {
	
	public void adaugaProdus(Produs produs);

	
	public void stergeProdus(int idProdus);

	
	public void updateProdus(Produs produs);

	
	public ArrayList<Produs> getAllProduse();

	
	public ArrayList<Produs> getAllProduseByDenumire(String dotare);

	
	public Produs getProdusById(int idProdus);

	
	public void verificaProdus(Produs produs);
}
