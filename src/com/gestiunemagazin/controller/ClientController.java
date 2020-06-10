package com.gestiunemagazin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.ClientDAOImpl;
import com.gestiunemagazin.model.Client;
import com.gestiunemagazin.util.GestiuneUtilities;



public class ClientController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/WEB-INF/client/client.jsp";
	private static String LIST_CLIENT = "/WEB-INF/client/listClient.jsp";
	private ClientDAOImpl clientDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public ClientController() {
		super();
		clientDAO = new ClientDAOImpl();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			int idClient = Integer.parseInt(request.getParameter("idClient"));
			clientDAO.stergeClient(idClient);
			forward = LIST_CLIENT;
			request.setAttribute("clienti", clientDAO.getAllClienti());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int idClient = Integer.parseInt(request.getParameter("idClient"));
			Client client = clientDAO.getClientById(idClient);
			request.getSession().setAttribute("client", client);
		} else if (action.equalsIgnoreCase("listClient")) {
			forward = LIST_CLIENT;
			request.setAttribute("clienti", clientDAO.getAllClienti());
		} else {
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Client clientRequest = (Client) request.getSession().getAttribute("client");
		String errorMessage = "";
		String forward = "";

		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		String cnp = request.getParameter("cnp");
		String adresa = request.getParameter("adresa");
		String telefon = request.getParameter("telefon");
		String email = request.getParameter("email");

		if (gestiuneUtilities.isNullOrEmpty(nume) || gestiuneUtilities.isNullOrEmpty(prenume)
				|| gestiuneUtilities.isNullOrEmpty(cnp) || gestiuneUtilities.isNullOrEmpty(adresa)
				|| gestiuneUtilities.isNullOrEmpty(telefon) || gestiuneUtilities.isNullOrEmpty(email)) {

			errorMessage = "Va rugam completati toate campurile!";
			forward = INSERT_OR_EDIT;
			request.setAttribute("errorMessage", errorMessage);
			request.getSession().setAttribute("client", clientRequest);
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		} else {

			Client client = new Client();
			client.setNume(nume);
			client.setPrenume(prenume);
			client.setCnp(cnp);
			client.setAdresa(adresa);
			client.setTelefon(telefon);
			client.setEmail(email);
			int idClient = request.getParameter("idClient") != null && !"".equals(request.getParameter("idClient"))
					? Integer.parseInt(request.getParameter("idClient"))
					: 0;
			client.setIdClient(idClient);

			clientDAO.verificaClient(client);

			RequestDispatcher view = request.getRequestDispatcher(LIST_CLIENT);
			request.setAttribute("clienti", clientDAO.getAllClienti());
			request.getSession().removeAttribute("client");
			view.forward(request, response);
		}
	}

}
