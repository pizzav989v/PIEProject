package com.gestiunemagazin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.AngajatDAOImpl;
import com.gestiunemagazin.model.Angajat;
import com.gestiunemagazin.util.GestiuneUtilities;



public class AngajatSearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String LIST_ANGAJAT = "/WEB-INF/angajat/listAngajat.jsp";
	private AngajatDAOImpl angajatDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public AngajatSearchController() {
		super();
		angajatDAO = new AngajatDAOImpl();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";

		String nume = request.getParameter("cautaNume");
		if (gestiuneUtilities.isNullOrEmpty(nume)) {
			request.setAttribute("angajati", angajatDAO.getAllAngajati());
			forward = LIST_ANGAJAT;
		} else {
			ArrayList<Angajat> angajati = angajatDAO.getAllAngajatiByNumeOrPren(nume.toLowerCase());
			request.setAttribute("angajati", angajati);
			forward = LIST_ANGAJAT;

		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
