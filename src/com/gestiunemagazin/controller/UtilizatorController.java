package com.gestiunemagazin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.UtilizatorDAOImpl;
import com.gestiunemagazin.model.Utilizator;
import com.gestiunemagazin.util.GestiuneUtilities;



public class UtilizatorController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/WEB-INF/utilizator/utilizator.jsp";
	private static String LIST_UTILIZATOR = "/WEB-INF/utilizator/listUtilizator.jsp";
	private UtilizatorDAOImpl utilizatorDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public UtilizatorController() {
		super();
		utilizatorDAO = new UtilizatorDAOImpl();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			int idUtilizator = Integer.parseInt(request.getParameter("idUtilizator"));
			utilizatorDAO.stergeUtilizator(idUtilizator);
			forward = LIST_UTILIZATOR;
			request.setAttribute("utilizatori", utilizatorDAO.getAllUtilizatori());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int idUtilizator = Integer.parseInt(request.getParameter("idUtilizator"));
			Utilizator utilizator = utilizatorDAO.getUtilizatorById(idUtilizator);
			request.getSession().setAttribute("utilizator", utilizator);
		} else if (action.equalsIgnoreCase("listUtilizator")) {
			forward = LIST_UTILIZATOR;
			request.setAttribute("utilizatori", utilizatorDAO.getAllUtilizatori());
		} else {
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utilizator utilizatorRequest = (Utilizator) request.getSession().getAttribute("utilizator");
		String errorMessage = "";
		String forward = "";

		String tipUtilizator = request.getParameter("tipUtilizator");
		String email = request.getParameter("email");
		String parola = request.getParameter("parola");

		if (gestiuneUtilities.isNullOrEmpty(tipUtilizator) || gestiuneUtilities.isNullOrEmpty(email)
				|| gestiuneUtilities.isNullOrEmpty(parola)) {

			errorMessage = "Va rugam completati toate campurile!";
			forward = INSERT_OR_EDIT;
			request.setAttribute("errorMessage", errorMessage);
			request.getSession().setAttribute("utilizator", utilizatorRequest);
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		} else {

			Utilizator utilizator = new Utilizator();
			utilizator.setTipUtilizator(Integer.parseInt(tipUtilizator));
			utilizator.setEmail(email);
			utilizator.setParola(parola);
			int idUtilizator = !gestiuneUtilities.isNullOrEmpty(request.getParameter("idUtilizator"))
					? Integer.parseInt(request.getParameter("idUtilizator"))
					: 0;
			utilizator.setIdUtilizator(idUtilizator);
			utilizatorDAO.verificaUtilizator(utilizator);

			RequestDispatcher view = request.getRequestDispatcher(LIST_UTILIZATOR);
			request.getSession().removeAttribute("utilizator");
			request.setAttribute("utilizatori", utilizatorDAO.getAllUtilizatori());
			view.forward(request, response);
		}
	}

}
