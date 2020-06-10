package com.gestiunemagazin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.ProdusDAOImpl;
import com.gestiunemagazin.model.Produs;
import com.gestiunemagazin.util.GestiuneUtilities;



public class ProdusController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/WEB-INF/produs/produs.jsp";
	private static String LIST_PRODUS = "/WEB-INF/produs/listProdus.jsp";
	private ProdusDAOImpl produsDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public ProdusController() {
		super();
		produsDAO = new ProdusDAOImpl();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			int idProdus = Integer.parseInt(request.getParameter("idProdus"));
			produsDAO.stergeProdus(idProdus);
			forward = LIST_PRODUS;
			request.setAttribute("produse", produsDAO.getAllProduse());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int idProdus = Integer.parseInt(request.getParameter("idProdus"));
			Produs produs = produsDAO.getProdusById(idProdus);
			request.getSession().setAttribute("produs", produs);
		} else if (action.equalsIgnoreCase("listProdus")) {
			forward = LIST_PRODUS;
			request.setAttribute("produse", produsDAO.getAllProduse());
		} else {
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Produs produsRequest = (Produs) request.getSession().getAttribute("produs");
		String errorMessage = "";
		String forward = "";

		String denumire = request.getParameter("denumire");
		String pret = request.getParameter("pret");
		String stoc = request.getParameter("stoc");
		String descriere = request.getParameter("descriere");

		if (gestiuneUtilities.isNullOrEmpty(denumire) || gestiuneUtilities.isNullOrEmpty(pret)
				|| gestiuneUtilities.isNullOrEmpty(stoc) || gestiuneUtilities.isNullOrEmpty(descriere)) {

			errorMessage = "Va rugam completati toate campurile!";
			forward = INSERT_OR_EDIT;
			request.setAttribute("errorMessage", errorMessage);
			request.getSession().setAttribute("produs", produsRequest);
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		} else {

			Produs produs = new Produs();
			produs.setDenumire(denumire);
			double pretD = Double.parseDouble(pret);
			produs.setPret(pretD);
			produs.setStoc(Integer.parseInt(stoc));
			produs.setDescriere(descriere);
			int idProdus = !gestiuneUtilities.isNullOrEmpty(request.getParameter("idProdus"))
					? Integer.parseInt(request.getParameter("idProdus"))
					: 0;
			produs.setIdProdus(idProdus);
			produsDAO.verificaProdus(produs);

			RequestDispatcher view = request.getRequestDispatcher(LIST_PRODUS);
			request.getSession().removeAttribute("produs");
			request.setAttribute("produse", produsDAO.getAllProduse());
			view.forward(request, response);
		}
	}

}
