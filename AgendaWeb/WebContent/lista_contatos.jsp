<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- IMPORTANDO O JSTL PARA USAR AS TABLIB -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- IMPORTANDO O CABECALHO -->
<c:import url="cabecalho.jsp"></c:import>

<jsp:useBean id="dao" class="br.edu.ifsudestemg.barbacena.agendaweb.dao.ContatoDAO" />
	<br><br>
	<div class="row">
		<div class="col col-sm-10 offset-1">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>NOME</th>
						<th>EMAIL</th>
						<th>ENDERECO</th>
						<th>TELEFONE</th>
						<th>DATA NASCIMENTO</th>
						<th>REMOVER</th>
						<th>ALTERAR</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="contato" items="${dao.listContatos()}" varStatus="id">
						<tr>
							<td>${contato.nome}</td>
							<td>${contato.email}</td>
							<td>${contato.endereco}</td>
							<td>${contato.telefone}</td>
							<td><fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy" /></td>
							<td><button onclick="remover(${contato.id})" class="btn btn-danger btn-sm">Remover</button></td>
							<td><button onclick="alterar(${contato.id})" class="btn btn-warning btn-sm">Alterar</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
		<div class="modal" id="exampleModal" role="dialog" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Cadastrar</h5>
	        <button type="button" onclick="$('.modal').hide();" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <div class="form_content">
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
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary btnCadastrar">Cadastrar</button>
	        <button type="button" class="btn btn-primary btnAtualizar">Salvar</button>
	        <button type="button" onclick="$('.modal').hide();" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>