<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lista de Tarefas</title>
</head>
<body>
	
	<div>
		<div>
			<table border="1" style="border-collapse: collapse; text-align: left;">
				<thead>
					<tr>
						<th>ID</th>
						<th>DESCRICAO</th>
						<th>DATA FINALZIAÇAO</th>
						<th>FINALIZAR</th>
						<th>ALTERAR</th>
						<th>REMOVER</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tarefas}" var="tarefa">
						<tr>
							<td>${tarefa.id}</td>
							<td>${tarefa.descricao}</td>
							<td> <fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy"/></td>
							
							<c:choose>
								<c:when test="${tarefa.finalizado eq true}">
									<td>Finalizado</td>
								</c:when>
								<c:otherwise>
									<td><button>Finalizar</button></td>
								</c:otherwise>
							</c:choose>
							
							<td><a href="/alterar-tarefa"><button>Alterar</button></a></td>
							<td><button>Remover</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>