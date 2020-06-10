package com.gestiunemagazin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.UtilizatorDAOImpl;
import com.gestiunemagazin.model.Utilizator;
import com.gestiunemagazin.util.GestiuneUtilities;



public class UtilizatorSearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String LIST_UTILIZATOR = "/WEB-INF/utilizator/listUtilizator.jsp";
	private UtilizatorDAOImpl utilizatorDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public UtilizatorSearchController() {
		super();
		utilizatorDAO = new UtilizatorDAOImpl();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = "";

		String nume = request.getParameter("cautaNume");
		String tipUser = request.getParameter("cautaTipUser");
		
		System.out.println("nume = " + nume);
		System.out.println("tipUser = " + tipUser);
		
		if (gestiuneUtilities.isNullOrEmpty(nume) && gestiuneUtilities.isNullOrEmpty(tipUser)) {
			request.setAttribute("utilizatori", utilizatorDAO.getAllUtilizatori());
			forward = LIST_UTILIZATOR;
		} else if(!gestiuneUtilities.isNullOrEmpty(nume) && gestiuneUtilities.isNullOrEmpty(tipUser)){
			ArrayList<Utilizator> utilizatori = utilizatorDAO.getAllUtilizatoriByEmail(nume.toLowerCase());
			request.setAttribute("utilizatori", utilizatori);
			forward = LIST_UTILIZATOR;

		}else if(gestiuneUtilities.isNullOrEmpty(nume) && !gestiuneUtilities.isNullOrEmpty(tipUser)){
			ArrayList<Utilizator> utilizatori = utilizatorDAO.getAllUtilizatoriByTipUser(tipUser);
			request.setAttribute("utilizatori", utilizatori);
			forward = LIST_UTILIZATOR;
		}else{
			ArrayList<Utilizator> utilizatori = utilizatorDAO.getAllUtilizatoriByEmailAndTipUser(nume.toLowerCase(), tipUser);
			request.setAttribute("utilizatori", utilizatori);
			forward = LIST_UTILIZATOR;
		}
		
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
