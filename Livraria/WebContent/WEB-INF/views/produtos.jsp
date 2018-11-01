<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp"/>
	
	<div class="row">
		<div class="col col-sm-4 mt-5 offset-1">
			<form class="form-inline" action="pesquisarTitulo">
			  <div class="form-group mx-sm-3 mb-2">
			    <label for="titulo" class="sr-only">Titulo</label>
			    <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Digite o titulo">
			  </div>
			  <button type="submit" class="btn btn-primary mb-2">Buscar</button>
			</form>
		</div>
	
		<div class="col col-sm-4 mt-5">
			<form class="form-inline" action="pesquisarCategoria">
			  <div class="form-group mx-sm-3 mb-2">
			    <label for="categoria" class="sr-only">Categoria</label>
			    <input type="text" class="form-control" id="categoria" name="categoria" placeholder="Digite a categoria">
			  </div>
			  <button type="submit" class="btn btn-primary mb-2">Buscar</button>
			</form>
		</div>
	</div>
	
	<div class="container mt-5">
		<c:if test="${not empty param.error_message}">
			<div class="alert alert-danger" role="alert">
				<c:out value="${param.error_message}"></c:out> 
			</div>
		</c:if>
	</div>
	
	<div class="container mt-5">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Id</th>
					<th>Titulo</th>
					<th>Autor</th>
					<th>Descrição</th>
					<th>Categoria</th>
					<th>Estoque</th>
					<th>Valor</th>
					<c:if test="${perfil == 'GERENTE' }">
						<th>Alterar</th>
					</c:if>
					<c:if test="${perfil != 'GERENTE' }">
						<th>Comprar</th>
					</c:if>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="livro" items="${livros}" >
				<tr>
					<td> ${livro.id}</td>
					<td> ${livro.titulo}</td>
					<td> ${livro.autor}</td>
					<td> ${livro.descricao}</td>
					<td> ${livro.categoria}</td>
					<td> ${livro.quantidade}</td>
					<td> ${livro.valor}</td>
					<c:if test="${perfil != 'GERENTE' }">
						<td><a href="addCart?id=${livro.id}">Comprar</a></td>
					</c:if>
					<c:if test="${perfil == 'GERENTE' }">
						<td><a class="btn" href="alterCart?id=${livro.id}">Alterar</a></td>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>