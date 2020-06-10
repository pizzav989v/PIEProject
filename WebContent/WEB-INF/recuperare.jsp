<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recuperare parola</title>
<style type="text/css">
  <%@include file="css/style.css" %>
</style>
</head>
<body>
<div class="login-page">
  <div class="form">
			<table>
				<c:choose>
					<c:when test="${not empty errorMessage}">
						<font color="red"><c:out value="${errorMessage}" /></font>
					</c:when>
					<c:when test="${not empty successMessage}">
						<font color="green"><c:out value="${successMessage}" /></font>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</table>
			<br/>
    <form method="POST" class="login-form" action='RecuperareController' name="frmLogin">
		<input type="text" placeholder="email" id="emailUser" name="emailUser"/>
		<input type="password" placeholder="noua parola" id="confirmaParola" name="confirmaParola"/>
      <input type="submit" value="Schimba parola"/>
      <p class="message">Te-ai razgandit?<a href="LogoutController?action=logout">Inapoi la pagina principala</a>
    </form>
  </div>
</div>
</body>
</html>