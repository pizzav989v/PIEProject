package com.gestiunemagazin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gestiunemagazin.daoimpl.ClientDAOImpl;
import com.gestiunemagazin.daoimpl.UtilizatorDAOImpl;
import com.gestiunemagazin.model.Utilizator;
import com.gestiunemagazin.util.GestiuneUtilities;



public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String INDEX_ADMIN = "/WEB-INF/indexAdmin.jsp";
	private static final String INDEX_OPERATOR = "/WEB-INF/indexOperator.jsp";
	private static final String INDEX_UTILIZATOR = "/WEB-INF/indexUtilizator.jsp";
	private static final String LOGIN = "/WEB-INF/login.jsp";
	private static final String RECUPERARE = "/WEB-INF/recuperare.jsp";
	private static final String INREGISTRARE = "/WEB-INF/inregistrare.jsp";

	private static final int ADMIN = 1;
	private static final int OPERATOR = 2;
	private static final int UTILIZATOR = 3;

	private UtilizatorDAOImpl utilizatorDAO;
	private ClientDAOImpl clientDAOImpl;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public LoginController() {
		super();
		utilizatorDAO = new UtilizatorDAOImpl();
		clientDAOImpl = new ClientDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String errorMessage = "";
		String action = request.getParameter("action");
		if (action != null && !action.isEmpty()) {
			if (action.equalsIgnoreCase("inregistrare")) {
				forward = INREGISTRARE;
			} else if (action.equalsIgnoreCase("recuperare")) {
				forward = RECUPERARE;
			}
		} else {
			String email = request.getParameter("email");
			String parola = request.getParameter("parola");
			if (gestiuneUtilities.isNullOrEmpty(email) || gestiuneUtilities.isNullOrEmpty(parola)) {
				errorMessage = "Va rugam completati toate campurile!";
				forward = LOGIN;
			} else {
				Utilizator utilizator = utilizatorDAO.getUtilizatorByEmailAndPass(email, parola);
				if (utilizator != null) {
					HttpSession session = request.getSession(true);
					session.setAttribute("user_email", email);
					session.setMaxInactiveInterval(30);
					int tipUtilizator = utilizator.getTipUtilizator();
					switch (tipUtilizator) {
					case ADMIN:
						forward = INDEX_ADMIN;
						break;
					case OPERATOR:
						forward = INDEX_OPERATOR;
						break;
					default:
					}
				} else {
					errorMessage = "Utilizator si/sau parola gresite!";
					forward = LOGIN;
				}
			}
		}
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
