package com.gestiunemagazin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.daoimpl.AngajatDAOImpl;
import com.gestiunemagazin.daoimpl.DepartamentDAOImpl;
import com.gestiunemagazin.model.Angajat;
import com.gestiunemagazin.model.Departament;
import com.gestiunemagazin.util.GestiuneUtilities;



public class AngajatController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/WEB-INF/angajat/angajat.jsp";
	private static String LIST_ANGAJAT = "/WEB-INF/angajat/listAngajat.jsp";
	private AngajatDAOImpl angajatDAO;
	private DepartamentDAOImpl departamentDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public AngajatController() {
		super();
		angajatDAO = new AngajatDAOImpl();
		departamentDAO = new DepartamentDAOImpl();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		ArrayList<Departament> departamenteList = departamentDAO.getAllDepartamente();

		if (action.equalsIgnoreCase("delete")) {
			int idAngajat = Integer.parseInt(request.getParameter("idAngajat"));
			angajatDAO.stergeAngajat(idAngajat);
			forward = LIST_ANGAJAT;
			request.setAttribute("angajati", angajatDAO.getAllAngajati());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int idAngajat = Integer.parseInt(request.getParameter("idAngajat"));
			Angajat angajat = angajatDAO.getAngajatById(idAngajat);
			request.getSession().setAttribute("angajat", angajat);
			request.setAttribute("departamenteList", departamenteList);
		} else if (action.equalsIgnoreCase("listAngajat")) {
			forward = LIST_ANGAJAT;
			request.setAttribute("angajati", angajatDAO.getAllAngajati());
		} else {
			request.setAttribute("departamenteList", departamenteList);
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Angajat angajatRequest = (Angajat) request.getSession().getAttribute("angajat");
		String errorMessage = "";
		String forward = "";

		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		String cnp = request.getParameter("cnp");
		String telefon = request.getParameter("telefon");
		String salariu = request.getParameter("salariu");
		String data = request.getParameter("dataAngajare");
		String functie = request.getParameter("functie");
		String idDepartament = request.getParameter("idDepartament");
		String email = request.getParameter("email");

		if (gestiuneUtilities.isNullOrEmpty(nume) || gestiuneUtilities.isNullOrEmpty(prenume)
				|| gestiuneUtilities.isNullOrEmpty(cnp) || gestiuneUtilities.isNullOrEmpty(telefon)
				|| gestiuneUtilities.isNullOrEmpty(salariu) || gestiuneUtilities.isNullOrEmpty(data)
				|| gestiuneUtilities.isNullOrEmpty(functie) || gestiuneUtilities.isNullOrEmpty(idDepartament)
				|| gestiuneUtilities.isNullOrEmpty(email)) {

			errorMessage = "Va rugam completati toate campurile!";
			forward = INSERT_OR_EDIT;
			request.setAttribute("errorMessage", errorMessage);
			request.getSession().setAttribute("angajat", angajatRequest);
			request.setAttribute("departamenteList", departamentDAO.getAllDepartamente());
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		} else {
			Angajat angajat = new Angajat();
			angajat.setNume(nume);
			angajat.setPrenume(prenume);
			angajat.setCnp(cnp);
			angajat.setTelefon(telefon);
			double salariuD = Double.parseDouble(salariu);
			angajat.setSalariu(salariuD);
			angajat.setFunctie(functie);
			int idDepartamentI = Integer.parseInt(idDepartament);
			angajat.setIdDepartament(idDepartamentI);

			try {
				Date dataAngajare = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				angajat.setDataAngajare(dataAngajare);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			angajat.setEmail(email);
			int idAngajat = request.getParameter("idAngajat") != null && !"".equals(request.getParameter("idAngajat"))
					? Integer.parseInt(request.getParameter("idAngajat"))
					: 0;
			angajat.setIdAngajat(idAngajat);

			angajatDAO.verificaAngajat(angajat);
			RequestDispatcher view = request.getRequestDispatcher(LIST_ANGAJAT);
			request.getSession().removeAttribute("angajat");
			request.setAttribute("angajati", angajatDAO.getAllAngajati());
			view.forward(request, response);
		}

	}
}
