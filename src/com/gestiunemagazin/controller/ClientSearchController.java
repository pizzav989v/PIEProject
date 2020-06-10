package com.gestiunemagazin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.ClientDAOImpl;
import com.gestiunemagazin.model.Client;
import com.gestiunemagazin.util.GestiuneUtilities;



public class ClientSearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String LIST_CLIENT = "/WEB-INF/client/listClient.jsp";
	private ClientDAOImpl clientDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public ClientSearchController() {
		super();
		clientDAO = new ClientDAOImpl();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";

		String nume = request.getParameter("cautaNume");
		System.out.println("nume = " + nume);
		if (gestiuneUtilities.isNullOrEmpty(nume)) {
			request.setAttribute("clienti", clientDAO.getAllClienti());
			forward = LIST_CLIENT;
		} else {
			ArrayList<Client> clienti = clientDAO.getAllClientiByNumeOrPren(nume.toLowerCase());
			System.out.println("size = " + clienti.size());
			request.setAttribute("clienti", clienti);
			forward = LIST_CLIENT;

		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
