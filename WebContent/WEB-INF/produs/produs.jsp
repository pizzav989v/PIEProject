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

<%	String user = (String) session.getAttribute("user_email");
	if (user == null || user.isEmpty())  {
		response.sendRedirect("./");
		return;
	}
%>

<title>Adauga Produs</title>
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
    <h3>Insereaza/Editeaza Produs </h3>
    <form method="POST" action='ProdusController' name="frmAdaugaProdus">
        <input type="hidden" name="idProdus" value="<c:out value="${produs.idProdus}" />" /> <br /> 
        <input type="text" name="denumire" placeholder = "Denumire" value="<c:out value="${produs.denumire}" />" /> <br /> 
        <input type="text" name="pret" placeholder = "Pret" value="<c:out value="${produs.pret}" />" /> <br />
        <input type="text" name="stoc" placeholder = "Stoc" value="<c:out value="${produs.stoc}" />" /> <br />
        <input type="text" name="descriere" placeholder = "Descriere" value="<c:out value="${produs.descriere}" />" /> <br />
 
       	<input type="submit" value="Submit" />
    </form>
   </div>
</div>
</body>
</html>