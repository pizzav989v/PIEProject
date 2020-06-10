<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<style type="text/css">
  <%@include file="../css/style.css" %>
</style>

<%	String user = (String) session.getAttribute("user_email");
	if (user == null || user.isEmpty())  {
		response.sendRedirect("./");
		return;
	}
%>

<title>Adauga Client</title>
</head>
<jsp:include page="../logout.jsp"/>
<body>
<br/><br/>
</div>
<div class="login-page">
  <div class="form">
	<table>
		<c:if test="${not empty errorMessage}">
			<font color="red"><c:out value="${errorMessage}" /></font>
		</c:if>
	</table>
	<br/>
    <h3>Insereaza/Editeaza Client </h3>
    <form method="POST" action='ClientController' name="frmAdaugaClient">
    	<input type="hidden" name="idClient" value="<c:out value="${client.idClient}" />" />
        <input type="text" placeholder="Nume" name="nume" value="<c:out value="${client.nume}" />" /> <br /> 
        <input type="text" name="prenume" placeholder="Prenume" value="<c:out value="${client.prenume}" />" /> <br /> 
        <input type="text" name = "cnp" placeholder="CNP" value="<c:out value="${client.cnp}"/>"/> <br />
        <input type="text" name="adresa" placeholder="Adresa" value="<c:out value="${client.adresa}" />" /> <br /> 
        <input type="text" name = "telefon" placeholder="Telefon" value="<c:out value="${client.telefon}"/>"/> <br />
        <input type="text" name="email" placeholder="Email" value="<c:out value="${client.email}" />" /> <br /> <br /> 
        
       	<input type="submit" value="Submit" />
    </form>
    </div>
    </div>
</body>
</html>