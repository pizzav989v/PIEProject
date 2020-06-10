package com.gestiunemagazin.dao;

import java.util.ArrayList;

import com.gestiunemagazin.model.Client;

public interface ClientDAO {

	public void adaugaClient(Client client);

	public void stergeClient(int idClient);

	public void updateClient(Client client);

	public ArrayList<Client> getAllClienti();

	public ArrayList<Client> getAllClientiByNumeOrPren(String nume);

	public Client getClientById(int idClient);

	public void verificaClient(Client client);

}
