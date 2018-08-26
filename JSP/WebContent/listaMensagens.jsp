<%@page import="java.util.Enumeration"%>
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
	
	Enumeration<String> enumeration = session.getAttributeNames();
	
	while( enumeration.hasMoreElements() ){
		Object oEmail = enumeration.nextElement();
		Object oMessage = session.getAttribute(oEmail.toString());
		out.print("Email: " + oEmail +", Mensagem: " + oMessage + "<br>");
	}
	%>
</body>
</html>