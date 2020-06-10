<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style type="text/css">
  <%@include file="css/style.css" %>
</style>
</head>
<body>
<div class="login-page">
  <div class="form">
	<table>
		<c:if test="${not empty errorMessage}">
			<font color="red"><c:out value="${errorMessage}" /></font>
		</c:if>
	</table> 
	<br/> 
    <form method="POST" class="login-form" action='LoginController' name="frmLogin">
      <input type="text" placeholder = "email..." id="email" name="email"/>
      <input type="password" placeholder = "parola..." id="parola" name="parola"/>
      <input type="submit" value="Login"/>
      <p class="message">Nu esti inregistrat? <a href="LoginController?action=inregistrare">Creeaza user</a>
	  <br>
	  Ai uitat parola? <a href="LoginController?action=recuperare">Recupereaza parola</a></p>
    </form>
  </div>
</div>
</body>
</html>