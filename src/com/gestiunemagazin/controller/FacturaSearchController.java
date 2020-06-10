package com.gestiunemagazin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.AngajatDAOImpl;
import com.gestiunemagazin.daoimpl.ClientDAOImpl;
import com.gestiunemagazin.daoimpl.FacturaDAOImpl;
import com.gestiunemagazin.model.Factura;
import com.gestiunemagazin.util.GestiuneUtilities;



public class FacturaSearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String LIST_FACTURI = "/WEB-INF/factura/listFactura.jsp";
	private FacturaDAOImpl facturaDAO;
	private ClientDAOImpl clientDAO;
	private AngajatDAOImpl angajatDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	public FacturaSearchController() {
		super();
		facturaDAO = new FacturaDAOImpl();
		clientDAO = new ClientDAOImpl();
		angajatDAO = new AngajatDAOImpl();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";

		String nume = request.getParameter("cautaNume");
		System.out.println("nume = " + nume);
		if (gestiuneUtilities.isNullOrEmpty(nume)) {
			request.setAttribute("facturi", facturaDAO.getAllFacturi());
			forward = LIST_FACTURI;
		} else {
			ArrayList<Factura> facturi = facturaDAO.getAllFacturiByNumeOrPren(nume.toLowerCase());
			request.setAttribute("facturi", facturi);
			forward = LIST_FACTURI;

		}
		request.setAttribute("clientiList", clientDAO.getAllClienti());
		request.setAttribute("angajatiList", angajatDAO.getAllAngajati());
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
