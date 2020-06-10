package com.gestiunemagazin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.UtilizatorDAOImpl;
import com.gestiunemagazin.model.Utilizator;



public class RecuperareController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String RECUPERARE = "/WEB-INF/recuperare.jsp";
	private UtilizatorDAOImpl utilizatorDAO;

	
	public RecuperareController() {
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
		String confirmareParola = request.getParameter("confirmaParola").trim();
		if (email.isEmpty() || confirmareParola.isEmpty()) {
			errorMessage = "Va rugam completati toate campurile!";
		} else {
			Utilizator utilizator = utilizatorDAO.getUtilizatorByEmail(email);
			if (utilizator == null) {
				errorMessage = "Utilizator inexistent!";
			} else {
				utilizator.setParola(confirmareParola);
				utilizatorDAO.verificaUtilizator(utilizator);
				successMessage = "Parola schimbata cu succes!";
			}
		}
		request.setAttribute("errorMessage", errorMessage);
		request.setAttribute("successMessage", successMessage);
		forward = RECUPERARE;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
