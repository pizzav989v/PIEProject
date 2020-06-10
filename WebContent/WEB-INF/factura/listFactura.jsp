<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FACTURI</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<%	String user = (String) session.getAttribute("user_email");
	if (user == null || user.isEmpty())  {
		response.sendRedirect("./");
		return;
	}
%>

<style>
#facturiTable {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#facturiTable td, #facturiTable th {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
}

#facturiTable tr:nth-child(even){background-color: #f2f2f2;}

#facturiTable tr:hover {background-color: #ddd;}

#facturiTable th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
    text-align: center;
}
#search-form_3 {
background: #e1e1e1; /* Fallback color for non-css3 browsers */
width: 365px;
margin: 10px auto;

/* Gradients */
background: -webkit-gradient( linear,left top, left bottom, color-stop(0, rgb(243,243,243)), color-stop(1, rgb(225,225,225)));
background: -moz-linear-gradient( center top, rgb(243,243,243) 0%, rgb(225,225,225) 100%);

/* Rounded Corners */
border-radius: 17px;
-webkit-border-radius: 17px;
-moz-border-radius: 17px;

/* Shadows */
box-shadow: 1px 1px 2px rgba(0,0,0,.3), 0 0 2px rgba(0,0,0,.3);
-webkit-box-shadow: 1px 1px 2px rgba(0,0,0,.3), 0 0 2px rgba(0,0,0,.3);
-moz-box-shadow: 1px 1px 2px rgba(0,0,0,.3), 0 0 2px rgba(0,0,0,.3);
}

/*** TEXT BOX ***/
.search_3{
background: #fafafa; /* Fallback color for non-css3 browsers */

/* Gradients */
background: -webkit-gradient( linear, left bottom, left top, color-stop(0, rgb(250,250,250)), color-stop(1, rgb(230,230,230)));
background: -moz-linear-gradient( center top, rgb(250,250,250) 0%, rgb(230,230,230) 100%);
border: 0;
border-bottom: 1px solid #fff;
border-right: 1px solid rgba(255,255,255,.8);
font-size: 16px;
margin: 4px;
padding: 5px;
width: 250px;

/* Rounded Corners */
border-radius: 17px;
-webkit-border-radius: 17px;
-moz-border-radius: 17px;

/* Shadows */
box-shadow: -1px -1px 2px rgba(0,0,0,.3), 0 0 1px rgba(0,0,0,.2);
-webkit-box-shadow: -1px -1px 2px rgba(0,0,0,.3), 0 0 1px rgba(0,0,0,.2);
-moz-box-shadow: -1px -1px 2px rgba(0,0,0,.3), 0 0 1px rgba(0,0,0,.2);
}

/*** USER IS FOCUSED ON TEXT BOX ***/
.search_3:focus{
outline: none;
background: #fff; /* Fallback color for non-css3 browsers */

/* Gradients */
background: -webkit-gradient( linear, left bottom, left top, color-stop(0, rgb(255,255,255)), color-stop(1, rgb(235,235,235)));
background: -moz-linear-gradient( center top, rgb(255,255,255) 0%, rgb(235,235,235) 100%);
}

/*** SEARCH BUTTON ***/
.submit_3{
background: #44921f;/* Fallback color for non-css3 browsers */
/* Gradients */
background: -webkit-gradient( linear, left top, left bottom, color-stop(0, rgb(79,188,32)), color-stop(0.15, rgb(73,157,34)), color-stop(0.88, rgb(62,135,28)), color-stop(1, rgb(49,114,21)));
background: -moz-linear-gradient( center top, rgb(79,188,32) 0%, rgb(73,157,34) 15%, rgb(62,135,28) 88%, rgb(49,114,21) 100%);
border: 0;
color: #eee;
cursor: pointer;
float: right;
font: 16px 'Raleway', sans-serif;
font-weight: bold;
height: 30px;
margin: 4px 4px 0;
text-shadow: 0 -1px 0 rgba(0,0,0,.3);
width: 84px;
outline: none;

/* Rounded Corners */
border-radius: 30px;
-webkit-border-radius: 30px;
-moz-border-radius: 30px;

/* Shadows */
box-shadow: -1px -1px 1px rgba(255,255,255,.5), 1px 1px 0 rgba(0,0,0,.4);
-moz-box-shadow: -1px -1px 1px rgba(255,255,255,.5), 1px 1px 0 rgba(0,0,0,.2);
-webkit-box-shadow: -1px -1px 1px rgba(255,255,255,.5), 1px 1px 0 rgba(0,0,0,.4);
}
/*** SEARCH BUTTON HOVER ***/
.submit_3:hover {
background: #4ea923; /* Fallback color for non-css3 browsers */

/* Gradients */
background: -webkit-gradient( linear, left top, left bottom, color-stop(0, rgb(89,222,27)), color-stop(0.15, rgb(83,179,38)), color-stop(0.8, rgb(66,143,27)), color-stop(1, rgb(54,120,22)));
background: -moz-linear-gradient( center top, rgb(89,222,27) 0%, rgb(83,179,38) 15%, rgb(66,143,27) 80%, rgb(54,120,22) 100%);
}
.submit_3:active {
background: #4ea923; /* Fallback color for non-css3 browsers */

/* Gradients */
background: -webkit-gradient( linear, left bottom, left top, color-stop(0, rgb(89,222,27)), color-stop(0.15, rgb(83,179,38)), color-stop(0.8, rgb(66,143,27)), color-stop(1, rgb(54,120,22)));
background: -moz-linear-gradient( center bottom, rgb(89,222,27) 0%, rgb(83,179,38) 15%, rgb(66,143,27) 80%, rgb(54,120,22) 100%);
}

</style>
</head>
<jsp:include page="../logout.jsp"/>

<form method="POST" action='FacturaSearchController' id="search-form_3">
	<input type="text" name="cautaNume" id="cautaNume" placeholder="cauta nume angajat..." class="search_3" /> 
	<input type="submit" class="submit_3" value="Cauta" />
</form>

<body>
<div>
	<table id="facturiTable" width="100%">
		<thead>
			<tr class="w3-green">
				<th>#</th>
				<th>Valoare</th>
				<th>Angajat</th>
				<th>Client</th>
				<th>Data</th>
				<th colspan=2>Actiune</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${facturi}" var="factura">
				<tr>
					<td><c:out value="${factura.idFactura}" /></td>
					<td><fmt:formatNumber pattern="0.0" value="${factura.valoare}" /></td>
					
					<c:forEach items="${angajatiList}" var="angajat">
						<c:if test = "${angajat.idAngajat == factura.idAngajat}">
							<td><c:out value="${angajat.nume.concat(' ').concat(angajat.prenume)}"/></td>
						</c:if>
					</c:forEach>
					
					<c:forEach items="${clientiList}" var="client">
						<c:if test = "${client.idClient == factura.idClient}">
							<td><c:out value="${client.nume.concat(' ').concat(client.prenume)}"/></td>
						</c:if>
					</c:forEach>
					
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${factura.data}" /></td>
					<td><a
						href="FacturaController?action=edit&idFactura=<c:out value="${factura.idFactura}"/>">Edit</a></td>
					<td><a
						href="FacturaController?action=delete&idFactura=<c:out value="${factura.idFactura}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="FacturaController?action=insert">Adauga Factura</a>
	</p>
</div>
</body>
</html>