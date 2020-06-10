<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
  <%@include file="../css/style.css" %>
</style>
<script>
  $( function() {
    $( "#data" ).datepicker({ minDate: 0 });
  } );
  </script>

<%	String user = (String) session.getAttribute("user_email");
	if (user == null || user.isEmpty())  {
		response.sendRedirect("./");
		return;
	}
%>

<title>Adauga Factura</title>
</head>
<jsp:include page="../logout.jsp"/>
<body>
<br/><br/>
<div class="login-page">
  <div class="form">
	<table>
		<c:if test="${not empty errorMessage}">
			<font color="red"><c:out value="${errorMessage}" /></font>
		</c:if>
	</table>
	<br/>  
    <h3>Insereaza/Editeaza Factura </h3>
    <form method="POST" action='FacturaController' name="frmAdaugaFactura">
        <input type="hidden" name="idFactura" value="<c:out value="${factura.idFactura}" />" /> <br /> 
        <input type="text" name="valoare" placeholder = "Valoare" value="<c:out value="${factura.valoare}" />" /> <br /> 
        <select name="idAngajat" id="angajat">
        	<option value="" disabled selected value>---selecteaza angajat---</option>
     		<c:forEach var="angajat" items="${angajatiList}">
          		<option value="${angajat.idAngajat}" ${angajat.idAngajat == factura.idAngajat ? 'selected' : ''}>${angajat.nume.concat(' ').concat(angajat.prenume)}</option>
      		</c:forEach>
		</select>
        <select name="idClient" id="idClient">
        	<option value="" disabled selected value>---selecteaza client---</option>
     		<c:forEach var="client" items="${clientiList}">
          		<option value="${client.idClient}" ${client.idClient == factura.idClient ? 'selected' : ''}>${client.nume.concat(' ').concat(client.prenume)}</option>
      		</c:forEach>
		</select> 
        <input type="text" name="data" placeholder = "Data" id="data" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${factura.data}" />" /> <br /> 
       	<input type="submit" value="Submit" />
    </form>
   </div>
</div>
</body>
</html>