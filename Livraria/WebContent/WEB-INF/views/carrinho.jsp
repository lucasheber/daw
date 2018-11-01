<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"/>

	<div class="container mt-5">
		<table class="table table-striped table-bordered">
			<thead>
				<tr class="text-center">
					<th>Livros</th>
					<th>Quantidade</th>
					<th>Valor Unitário</th>
					<th>Valor Total</th>
					<th>Remover</th>
				</tr>
			<thead>
			<tbody>
				<c:forEach var="item" items="${ carrinho.values() }">
				<tr class="text-center">
					<td>${item.livro.titulo}</td>
					<td> 
						<div class="text-center">
							<a href="popProdCart?id=${item.livro.id}">
								<i class="fas fa-minus"></i>
							</a>
							
							<input class="text-center ml-3 mr-3" style=" width: 30px;" disabled="disabled" type="number" value="${item.quant}"> 
								
							<a href="pushPrdCart?id=${item.livro.id}">
								<i class="fas fa-plus"></i>
							</a>
						</div>
					</td>
					<td>${item.valor }</td>
					<td>${item.valor_total }</td>
					<td><a href="finCart?id=${item.livro.id}"><i class="fas fa-trash-alt"></i></a></td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr class="text-right">
					<td colspan="4">Total: </td>
					<td>R$ ${carrinho_total}</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
	<div class="row mt-5">
		<div class="col col-sm-4 offset-4">
			<a href="finCart" class="btn btn-success btn-block">Finalizar</a>
		</div>
	</div>

</body>
</html>