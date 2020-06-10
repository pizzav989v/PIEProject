package com.gestiunemagazin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gestiunemagazin.daoimpl.UtilizatorDAOImpl;
import com.gestiunemagazin.model.Utilizator;



public class LogoutController extends HttpServlet {
	private static String LOGIN = "/WEB-INF/login.jsp";
	private static String INDEX = "/WEB-INF/indexAdmin.jsp";
	private static String INDEX_OP = "/WEB-INF/indexOperator.jsp";
	private static final long serialVersionUID = 1L;
	private UtilizatorDAOImpl utilizatorDAO;

	
	public LogoutController() {
		super();
		utilizatorDAO = new UtilizatorDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		if ("logout".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate();
			forward = LOGIN;
		} else if (action.equalsIgnoreCase("index")) {
			HttpSession session = request.getSession(true);
			String email = (String) session.getAttribute("user_email");
			Utilizator utilizator = utilizatorDAO.getUtilizatorByEmail(email);
			if (utilizator != null) {
				if (utilizator.getTipUtilizator() == 1) {
					forward = INDEX;
				} else {
					forward = INDEX_OP;
				}
				session.setAttribute("user_email", email);
				session.setMaxInactiveInterval(30);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
