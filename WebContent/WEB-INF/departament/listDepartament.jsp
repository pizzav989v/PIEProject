<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DEPARTAMENTE</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<%	String user = (String) session.getAttribute("user_email");
	if (user == null || user.isEmpty())  {
		response.sendRedirect("./");
		return;
	}
%>

<style>
#departamenteTable {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#departamenteTable td, #departamenteTable th {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
}

#departamenteTable tr:nth-child(even){background-color: #f2f2f2;}

#departamenteTable tr:hover {background-color: #ddd;}

#departamenteTable th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
    text-align: center;
}
</style>
</head>
<jsp:include page="../logout.jsp"/>
<body>
<br/><br/>
<div>
	<table id="departamenteTable" width="100%">
		<thead>
			<tr class="w3-green">
				<th>#</th>
				<th>Nume departament</th>
				<th colspan=2>Actiune</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${departamente}" var="departament">
				<tr>
					<td><c:out value="${departament.idDepartament}" /></td>
					<td><c:out value="${departament.numeDepartament}" /></td>
					<td><a
						href="DepartamentController?action=edit&idDepartament=<c:out value="${departament.idDepartament}"/>">Edit</a></td>
					<td><a
						href="DepartamentController?action=delete&idDepartament=<c:out value="${departament.idDepartament}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="DepartamentController?action=insert">Adauga Departament</a>
	</p>
</div>
</body>
</html>