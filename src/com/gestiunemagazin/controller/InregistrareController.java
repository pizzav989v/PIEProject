package com.gestiunemagazin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.UtilizatorDAOImpl;
import com.gestiunemagazin.model.Utilizator;



public class InregistrareController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String INREGISTRARE = "/WEB-INF/inregistrare.jsp";
	private UtilizatorDAOImpl utilizatorDAO;

	
	public InregistrareController() {
		super();
		utilizatorDAO = new UtilizatorDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorMessage = "";
		String successMessage = "";
		String forward = "";
		String email = request.getParameter("emailUser").trim();
		String parola = request.getParameter("parola").trim();
		String confirmareParola = request.getParameter("confirmaParola").trim();
		if (email.isEmpty() || parola.isEmpty() || confirmareParola.isEmpty()) {
			errorMessage = "Va rugam completati toate campurile!";
		} else {
			if (!parola.equals(confirmareParola)) {
				errorMessage = "Parolele nu se potrivesc!";
			} else {
				System.out.println("email = " + email);
				Utilizator utilizator = utilizatorDAO.getUtilizatorByEmail(email);
				if (utilizator != null) {
					errorMessage = "Utilizator deja existent!";
				} else {
					Utilizator utilizatorNou = new Utilizator();
					utilizatorNou.setIdUtilizator(0);
					utilizatorNou.setEmail(email);
					utilizatorNou.setParola(parola);
					utilizatorNou.setTipUtilizator(3); // default pentru utilizator
					utilizatorDAO.verificaUtilizator(utilizatorNou);
					successMessage = "Utilizator creat cu succes!";
				}
			}
		}
		request.setAttribute("errorMessage", errorMessage);
		request.setAttribute("successMessage", successMessage);
		forward = INREGISTRARE;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
