<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp"/>
	
	<div class="container mt-5">
		<div class="row">
			<div class="col col-sm-12">
				<h4 class="text-center">Registrar</h4><hr>
			</div>
		</div>
		
		<div class="row">
			<div class="col col-sm-5 offset-4">
				<form class="form" action="registerUser" method="post">
					
					<label for="login">Username</label>	
					<input value="${usuariologado eq true ? usuario.login : ''}" type="text" name="login" class="form-control" placeholder="Username" required autofocus>
	
					<label class="mt-3" for="senha">Password</label>
					<input value="${usuariologado eq true ? usuario.senha : ''}" type="password" name="senha" class="form-control" placeholder="Password" required>
					
					<input type="hidden" name="perfil" value="USER">
			
					<button class="btn btn-primary btn-block mt-4" type="submit">Registrar</button>
					<p class="mt-5 mb-3 text-muted text-center">&copy; E-Livros 2018</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>