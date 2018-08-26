<%@page import="org.apache.jasper.tagplugins.jstl.core.Param"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border=1>
		<thead>
			<%
				double minGrau = -40;
				double maxGrau = 100;
				double step = 10;
			
				for( double grau = minGrau; grau <= maxGrau; grau += step ){
			%>
				<tr>
					<th><%= grau %></th>
					<td><%= (9 / 5 * grau + 32) %></td>
				</tr>
			<% } %>
			
			<%
				String temperatura = request.getParameter("temperatura");
				
				if( temperatura != null ){
					double temp = Double.parseDouble(temperatura);

				%>
					<tr>
						<th><%= temp %></th>
						<td><%= (9 / 5 * temp + 32) %></td>
					</tr>
				<% }// if %>
		</thead>
	</table>
	<br><br>
	
	<form action="" method="post">
		Temperatura: <br>
		<input type="number" name="temperatura">
		<button type="submit">Enviar</button>
	</form>
</body>
</html>