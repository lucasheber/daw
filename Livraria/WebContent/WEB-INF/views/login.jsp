<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="header.jsp" %>

	<div class="row">
		<div class="col col-sm-4 offset-4">
			<h1 class="h3 mt-5 mb-3 font-weight-normal">Login</h1>
			
			<form class="form" action="logar" method="post">
				
				<label for="login" class="sr-only">Email address</label>	
				<input type="text" name="login" class="form-control" placeholder="Username" required autofocus>

				<label class="mt-3" for="senha" class="sr-only">Password</label>
				<input type="password" name="senha" class="form-control" placeholder="Password" required>
				
				<button class="btn btn-primary btn-block mt-4" type="submit">Sign in</button>
				<p class="mt-5 mb-3 text-muted text-center">&copy; E-Livros 2018</p>
				
			</form>
		</div>
	</div>

	<div class="container col-sm-4 mt-2">
		<c:if test="${not empty param.error_login}">
			<div class="alert alert-danger" role="alert">
				<c:out value="${param.error_login}"></c:out> 
			</div>
		</c:if>
		
		<c:if test="${not empty param.success_user}">
			<div class="alert alert-success" role="alert">
				<c:out value="${param.success_user}"></c:out> 
			</div>
		</c:if>
	</div>
	
</body>
</html>