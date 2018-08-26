<%@page import="java.util.Enumeration"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		
		String email = request.getParameter("email");
		String message = request.getParameter("mensagem");
		
		if( email == null || message == null ) return;
		
		session.setAttribute(email, message);
		
	%>
</body>
</html>