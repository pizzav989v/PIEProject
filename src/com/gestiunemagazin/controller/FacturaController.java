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
import com.gestiunemagazin.daoimpl.ClientDAOImpl;
import com.gestiunemagazin.daoimpl.FacturaDAOImpl;
import com.gestiunemagazin.model.Angajat;
import com.gestiunemagazin.model.Client;
import com.gestiunemagazin.model.Factura;
import com.gestiunemagazin.util.GestiuneUtilities;



public class FacturaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/WEB-INF/factura/factura.jsp";
	private static String LIST_SEJUR = "/WEB-INF/factura/listFactura.jsp";
	private FacturaDAOImpl facturaDAO;
	private ClientDAOImpl clientDAO;
	private AngajatDAOImpl angajatDAO;
	private GestiuneUtilities gestiuneUtilities = GestiuneUtilities.getInstance();

	
	public FacturaController() {
		super();
		facturaDAO = new FacturaDAOImpl();
		clientDAO = new ClientDAOImpl();
		angajatDAO = new AngajatDAOImpl();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		ArrayList<Client> clientiList = clientDAO.getAllClienti();
		ArrayList<Angajat> angajatiList = angajatDAO.getAllAngajati();

		if (action.equalsIgnoreCase("delete")) {
			int idFactura = Integer.parseInt(request.getParameter("idFactura"));
			facturaDAO.stergeFactura(idFactura);
			forward = LIST_SEJUR;
			request.setAttribute("facturi", facturaDAO.getAllFacturi());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int idFactura = Integer.parseInt(request.getParameter("idFactura"));
			Factura factura = facturaDAO.getFacturaById(idFactura);
			request.getSession().setAttribute("factura", factura);
		} else if (action.equalsIgnoreCase("listFactura")) {
			forward = LIST_SEJUR;
			request.setAttribute("facturi", facturaDAO.getAllFacturi());
		} else {
			forward = INSERT_OR_EDIT;
		}
		request.setAttribute("clientiList", clientiList);
		request.setAttribute("angajatiList", angajatiList);
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Factura facturaRequest = (Factura) request.getSession().getAttribute("factura");
		String errorMessage = "";
		String forward = "";

		String valoare = request.getParameter("valoare");
		String idClient = request.getParameter("idClient");
		String idAngajat = request.getParameter("idAngajat");
		String data = request.getParameter("data");

		if (gestiuneUtilities.isNullOrEmpty(valoare) || gestiuneUtilities.isNullOrEmpty(idClient)
				|| gestiuneUtilities.isNullOrEmpty(idAngajat) || gestiuneUtilities.isNullOrEmpty(data)) {
			errorMessage = "Va rugam completati toate campurile!";
			forward = INSERT_OR_EDIT;
			request.setAttribute("errorMessage", errorMessage);
			request.getSession().setAttribute("factura", facturaRequest);
			request.setAttribute("clientiList", clientDAO.getAllClienti());
			request.setAttribute("angajatiList", angajatDAO.getAllAngajati());
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		} else {

			Factura factura = new Factura();

			double valoareD = Double.parseDouble(valoare);
			factura.setValoare(valoareD);
			int idClientI = Integer.parseInt(idClient);
			factura.setIdClient(idClientI);
			int idAngajatI = Integer.parseInt(idAngajat);
			factura.setIdAngajat(idAngajatI);
			try {
				Date dataD = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				factura.setData(dataD);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int idFactura = !gestiuneUtilities.isNullOrEmpty(request.getParameter("idFactura"))
					? Integer.parseInt(request.getParameter("idFactura"))
					: 0;
			factura.setIdFactura(idFactura);
			facturaDAO.verificaFactura(factura);

			RequestDispatcher view = request.getRequestDispatcher(LIST_SEJUR);
			request.getSession().removeAttribute("factura");
			request.setAttribute("facturi", facturaDAO.getAllFacturi());
			request.setAttribute("clientiList", clientDAO.getAllClienti());
			request.setAttribute("angajatiList", angajatDAO.getAllAngajati());
			view.forward(request, response);
		}
	}
}
