<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario</title>
</head>
<body>
	<div>
		<div>
			<form action="adicionaTarefa" method="post">
				<label>Decrição</label><br>
				<textarea rows="5" cols="25" name="descricao"></textarea><br><br>
				
				<button type="submit">Adicionar</button>
			</form>
		</div>
	</div>
</body>
</html>