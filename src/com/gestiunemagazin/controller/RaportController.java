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
import com.gestiunemagazin.model.Angajat;
import com.gestiunemagazin.util.PDFGenerator;



public class RaportController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AngajatDAOImpl angajatDAO;
	private ClientDAOImpl clientDAO;

	
	public RaportController() {
		super();
		angajatDAO = new AngajatDAOImpl();
		clientDAO = new ClientDAOImpl();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		response.setContentType("application/pdf");

		if (action.equalsIgnoreCase("raportAngajat")) {
			ArrayList<Angajat> angajati = angajatDAO.getAllAngajatiBetweenDate();
			PDFGenerator.getInstance().genereazaRaportAngajati(response, angajati);
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
