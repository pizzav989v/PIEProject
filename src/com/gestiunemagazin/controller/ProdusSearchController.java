package com.gestiunemagazin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.ProdusDAOImpl;
import com.gestiunemagazin.model.Produs;
import com.gestiunemagazin.util.GestiuneUtilities;



public class ProdusSearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String LIST_CAMERA = "/WEB-INF/produs/listProdus.jsp";
	private ProdusDAOImpl produsDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public ProdusSearchController() {
		super();
		produsDAO = new ProdusDAOImpl();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";

		String nume = request.getParameter("cautaNume");
		if (gestiuneUtilities.isNullOrEmpty(nume)) {
			request.setAttribute("produse", produsDAO.getAllProduse());
			forward = LIST_CAMERA;
		} else {
			ArrayList<Produs> produse = produsDAO.getAllProduseByDenumire(nume.toLowerCase());
			request.setAttribute("produse", produse);
			forward = LIST_CAMERA;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
