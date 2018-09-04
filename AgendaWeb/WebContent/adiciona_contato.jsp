
<!-- IMPORTANDO O JSTL PARA USAR AS TABLIB -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!-- IMPORTANDO O CABECALHO -->
<c:import url="cabecalho.jsp"></c:import>
	<div class="row">
		<div class="col col-sm-4 offset-4">
			<form class="form">
				<div class="form-group">
					<label for="nome">Nome</label> 
					<input type="text" class="form-control" data-id="" id="nome" name="nome" placeholder="Digite o nome">
				</div>
				<div class="form-group">
					<label for="email">Email</label> 
					<input type="email" class="form-control" id="email" name="email" placeholder="Digite o email">
				</div>
				<div class="form-group">
					<label for="endereco">Enderco</label> 
					<input type="text" class="form-control" id="endereco" name="endereco" placeholder="Digite o endereco">
				</div>
				<div class="form-group">
					<label for="telefone">Telefone</label> 
					<input type="tel" class="form-control" id="telefone" name="telefone" placeholder="Digite o telefone">
				</div>
				<div class="form-group">
					<label for="data">Data de nascimento</label> 
					<input type="text" class="form-control" id="data" name="data_nascimento" placeholder="Digite a data de nascimento">
				</div>
			</form>
		</div>
	</div>
	
	<div class="row">
		<div class="offset-6">
			<button type="button" class="btn btn-primary btn-block btnCadastrar">Adicionar</button>
		</div>
	</div>
</body>
</html>