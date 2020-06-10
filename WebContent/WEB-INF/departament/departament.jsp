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

<title>Adauga Departament</title>
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
    <h3>Insereaza/Editeaza Departament </h3>
    <form method="POST" action='DepartamentController' name="frmAdaugaDepartament">
        <input type="hidden" name="idDepartament" value="<c:out value="${departament.idDepartament}" />" /> <br /> 
        <input type="text" name="numeDepartament" placeholder = "Nume departament" value="<c:out value="${departament.numeDepartament}" />" /> <br /> 
        
       	<input type="submit" value="Submit" />
    </form>
   </div>
</div>
</body>
</html>