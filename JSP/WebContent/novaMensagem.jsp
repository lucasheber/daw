<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NOVA MENSAGEM :: JSP</title>
<style type="text/css">
	.form { width: 300px; height: 200px; margin: 0 auto; padding-top: 30px; }
</style>
</head>
<body>
	<div class="form">
		<form action="gravarMensagem.jsp" method="post" target="_parent">
			E-mail: <br>
			<input type="email" name="email"> <br> <br>
			Mensagem: <br>
			<textarea rows="5" cols="20" name="mensagem"></textarea>
			<br><br>
			<button type="submit">Enviar</button>
		</form>
	</div>
</body>
</html>