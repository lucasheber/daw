<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp"/>

<div class="container mt-2">
		<div class="row">
			<div class="col col-sm-12">
				<h4 class="text-center">Cadastrar Produto</h4><hr>
			</div>
		</div>
		
		<div class="row mt-2">
			<div class="col col-sm-5 offset-4">
				<form class="form" action="cadastrarProduto" method="post">
					
					<input type="hidden" name="id" value="${livro.id}">
					
					<label for="titulo">Titulo</label>	
					<input value="${livro.id != null ? livro.titulo : '' }" type="text" name="titulo" class="form-control" placeholder="Titulo" required autofocus>
	
					<label class="mt-3" for="autor">Autor</label>
					<input value="${livro.id != null ? livro.autor : '' }" type="text" name="autor" class="form-control" placeholder="Autor" required>
					
					<label class="mt-3" for="descricao">Descrição</label>
					<input value="${livro.id != null ? livro.descricao : '' }" type="text" name="descricao" class="form-control" placeholder="Descrição" required>
					
					<label class="mt-3" for="categoria">Categoria</label>
					<input value="${livro.id != null ? livro.categoria : '' }" type="text" name="categoria" class="form-control" placeholder="Categoria" required>
					
					<label class="mt-3" for="valor">Valor</label>
					<input value="${livro.id != null ? livro.valor : '' }" type="number" name="valor" class="form-control" placeholder="Valor" required>
					
					<label class="mt-3" for="quantidade">Qtd. Estoque</label>
					<input value="${livro.id != null ? livro.quantidade : '' }" type="number" name="quantidade" class="form-control" placeholder="Quantidade em Estoque" required>
								
					<button class="btn btn-success btn-block mt-4" type="submit">${livro.id == null ? 'Cadastrar' : 'Alterar'}</button>
					<p class="mt-5 mb-3 text-muted text-center">&copy; E-Livros 2018</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>