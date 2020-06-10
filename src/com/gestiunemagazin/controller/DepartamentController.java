package com.gestiunemagazin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.DepartamentDAOImpl;
import com.gestiunemagazin.model.Departament;
import com.gestiunemagazin.util.GestiuneUtilities;



public class DepartamentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/WEB-INF/departament/departament.jsp";
	private static String LIST_DEPARTAMENT = "/WEB-INF/departament/listDepartament.jsp";
	private DepartamentDAOImpl departamentDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public DepartamentController() {
		super();
		departamentDAO = new DepartamentDAOImpl();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			int idDepartament = Integer.parseInt(request.getParameter("idDepartament"));
			departamentDAO.stergeDepartament(idDepartament);
			forward = LIST_DEPARTAMENT;
			request.setAttribute("departamente", departamentDAO.getAllDepartamente());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int idDepartament = Integer.parseInt(request.getParameter("idDepartament"));
			Departament departament = departamentDAO.getDepartamentById(idDepartament);
			request.getSession().setAttribute("departament", departament);
		} else if (action.equalsIgnoreCase("listDepartament")) {
			forward = LIST_DEPARTAMENT;
			request.setAttribute("departamente", departamentDAO.getAllDepartamente());
		} else {
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Departament departamentRequest = (Departament) request.getSession().getAttribute("departament");
		String errorMessage = "";
		String forward = "";

		String numeDepartament = request.getParameter("numeDepartament");
		if (gestiuneUtilities.isNullOrEmpty(numeDepartament)) {
			errorMessage = "Va rugam completati toate campurile!";
			forward = INSERT_OR_EDIT;
			request.setAttribute("errorMessage", errorMessage);
			request.getSession().setAttribute("departament", departamentRequest);
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		} else {

			Departament departament = new Departament();
			departament.setNumeDepartament(numeDepartament);
			int idDepartament = !gestiuneUtilities.isNullOrEmpty(request.getParameter("idDepartament"))
					? Integer.parseInt(request.getParameter("idDepartament"))
					: 0;
			departament.setIdDepartament(idDepartament);
			departamentDAO.verificaDepartament(departament);

			RequestDispatcher view = request.getRequestDispatcher(LIST_DEPARTAMENT);
			request.getSession().removeAttribute("departament");
			request.setAttribute("departamente", departamentDAO.getAllDepartamente());
			view.forward(request, response);
		}
	}
}
