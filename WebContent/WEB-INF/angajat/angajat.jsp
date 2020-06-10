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

<%	String user = (String) session.getAttribute("user_email");
	if (user == null || user.isEmpty())  {
		response.sendRedirect("./");
		return;
	}
%>

<style type="text/css">
  <%@include file="../css/style.css" %>
</style>
<script>
  $( function() {
    $( "#dataAngajare" ).datepicker({ minDate: 0 });
  } );
  </script>

<title>Adauga Angajat</title>
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
    <h3>Insereaza/Editeaza Angajat </h3>
    <form method="POST" action='AngajatController' name="frmAdaugaAngajat">
        <input type="hidden" name="idAngajat" value="<c:out value="${angajat.idAngajat}" />" /> <br /> 
        <input type="text" name="nume" placeholder = "Nume" value="<c:out value="${angajat.nume}" />" /> <br /> 
        <input type="text" name="prenume" placeholder = "Prenume" value="<c:out value="${angajat.prenume}" />" /> <br /> 
        <input type="text" name="cnp" placeholder = "CNP" value="<c:out value="${angajat.cnp}"/>"/> <br />
        <input type="text" name="telefon" placeholder = "Telefon" value="<c:out value="${angajat.telefon}"/>"/> <br />
        <input type="text" name="salariu" placeholder = "Salariu" value="<c:out value="${angajat.salariu}"/>"/> <br />
        <input type="text" name="dataAngajare" placeholder = "Data angajare" id="dataAngajare" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${angajat.dataAngajare}" />" /> <br />
        <input type="text" name="functie" placeholder = "Functie" value="<c:out value="${angajat.functie}" />" /> <br />  
        <select name="idDepartament" id="idDepartament">
        	<option value="" disabled selected value>---selecteaza departament---</option>
     		<c:forEach var="departament" items="${departamenteList}">
          		<option value="${departament.idDepartament}" ${departament.idDepartament == angajat.idDepartament ? 'selected' : ''}>${departament.numeDepartament}</option>
      		</c:forEach>
		</select>
        
        <input type="text" name="email" placeholder = "Email" value="<c:out value="${angajat.email}" />" /> <br /> 
        
       	<input type="submit" value="Submit" />
    </form>
   </div>
</div>
</body>
</html>