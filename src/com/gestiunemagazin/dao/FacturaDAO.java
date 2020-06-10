package com.gestiunemagazin.dao;

import java.util.ArrayList;

import com.gestiunemagazin.model.Factura;

public interface FacturaDAO {

	public void adaugaFactura(Factura factura);

	public void stergeFactura(int idFactura);

	public void updateFactura(Factura factura);

	public ArrayList<Factura> getAllFacturi();

	public ArrayList<Factura> getAllFacturiByNumeOrPren(String numeAng);

	public Factura getFacturaById(int idFactura);

	public void verificaFactura(Factura factura);
}
