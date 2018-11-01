<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
	
	<div class="container mt-5">
	<div class="row">
	  <div class="col-sm-4 mt-2">
	    <div class="card">
	      <div class="card-body">
	        <h5 class="card-title">Login</h5>
	        <p class="card-text">Clique no botão abaixo para realizar o login no sistema!</p>
	        <a href="login" class="btn btn-primary btn-block ${usuariologado == true ? 'disabled' : '' }">Entrar</a>
	      </div>
	    </div>
	  </div>
	  
	  <div class="col-sm-4 mt-2">
	    <div class="card">
	      <div class="card-body">
	      	<c:if test="${usuariologado eq true}">
	      		<h5 class="card-title">Alterar</h5>
	      	</c:if>
	      	<c:if test="${empty usuariologado}">
	      		<h5 class="card-title">Cadastrar</h5>
	      	</c:if>
	       
	        <p class="card-text">Clique no botão abaixo para cadastrar/ alterar seus dados!</p>
	        <a href="${usuariologado eq true ? 'alter' : 'register'}" class="btn btn-primary btn-block">Cadastro</a>
	      </div>
	    </div>
	  </div>
	  
	  <div class="col-sm-4 mt-2">
	    <div class="card">
	      <div class="card-body">
	        <h5 class="card-title">Produtos</h5>
	        <p class="card-text">Clique no botão abaixo ver os produtos! <span class="font-italic text-primary">#partiuCompras</span></p>
	        <a href="produtos" class="btn btn-primary btn-block">Loja</a>
	      </div>
	    </div>
	  </div>
	</div>
	
	<c:if test="${perfil eq 'ADMIN'}">
		<div class="row">
			<div class="col-sm-4 mt-2">
		    	<div class="card">
			      <div class="card-body">
			        <h5 class="card-title">Cadastrar Gerente</h5>
			        <p class="card-text">Clique no botão abaixo para cadastrar o gerente do sistema!</p>
			        <a href="admin" class="btn btn-primary btn-block">Cadastrar Gerente</a>
			      </div>
			    </div>
		  	</div>
		</div>
	</c:if>
	</div>
</body>
</html>