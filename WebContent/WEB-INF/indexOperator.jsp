<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Index Operator</title>
<style type="text/css">
  <%@include file="css/style_admin.css" %>
</style>
</head>
<style>
a.button {
	-webkit-appearance: button;
	-moz-appearance: button;
	appearance: button;
	background: #ffffff none repeat scroll 0 0;
	border: 1px solid #aa8d56;
	color: #006600;
	outline: medium none;
	padding: 10px;
	text-decoration: none;
	align: center;
    width:150px;
    height:20px;
}
</style>
<jsp:include page="logout.jsp"/>
<body>
<div class="login-page" align = "center">
</div>
  <div class="form">
  <h3>Alege una dintre tabelele urmatoare: </h3>
   
    <a href="ClientController?action=listClient" class="button">Client</a><br /><br />
    <a href="FacturaController?action=listFactura" class="button">Factura</a><br /><br />
    <a href="ProdusController?action=listProdus" class="button">Produs</a><br /><br />
  </div>
</body>
</html>