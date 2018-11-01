<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp"/>

<div class="container mt-5">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>Pedido</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pedido" items="${pedidos}" >
				<tr>
					<td> ${pedido.id}</td>
					<td>
						<c:forEach var="item" items="${pedido.itens}" >
							<p class="text-center">${item.livro.titulo}</p>
						</c:forEach>
					</td>
					<td>R$ ${pedido.total}</td> 
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>