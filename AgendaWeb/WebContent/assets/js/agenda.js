// INICICIALIA O JQUERY
$(function(){
	
	// SETA A POSICAO PADRAO DA MENSAGEM PARA O TOPO À DIREITA
	alertify.set('notifier','position', 'top-right');
	
	/* ***************************** */
	/* ******** CADASTRAR ********** */
	$(".btnCadastrar").click(function(){
		
		// VERIFICA SE OS DADOS FORAM PREENCHIDOS
		if( !validaDados() ) return;

		$.post("adicionaContato", {
			nome: $("input[name=nome]").val(),
			email: $("input[name=email]").val(),
			telefone: $("input[name=telefone]").val(),
			endereco: $("input[name=endereco]").val(),
			data_nascimento: $("input[name=data_nascimento]").val()
		})// post
		.done(function(data){
			data = JSON.parse(data);
			
			if( data.status == "success" ){
				msgSuccess( "Contato cadastrado com sucesso!" );
				limpaCampos();
				
				carregaTabela();
			} else 
				msgError( "Ocorreu um erro no envio dos dados!" );
			
		})// done
		.fail(function(){
			msgError( "Ocorreu um erro no envio dos dados!" );
		});// fail
		
	});// click
	
	$(".btnAtualizar").click(function(){
		// VERIFICA SE OS DADOS FORAM PREENCHIDOS
		if( !validaDados() ) return;

		$.post("atualizaContato", {
			id: $("input[name=nome]").attr("data-id"),
			nome: $("input[name=nome]").val(),
			email: $("input[name=email]").val(),
			telefone: $("input[name=telefone]").val(),
			endereco: $("input[name=endereco]").val(),
			data_nascimento: $("input[name=data_nascimento]").val()
		})// post
		.done(function(data){
			data = JSON.parse(data);
			
			if( data.status == "success" ){
				$('.modal').hide();
				
				msgSuccess( "Contato atualizado com sucesso!" );
				limpaCampos();
				
				carregaTabela();
			} else 
				msgError( "Ocorreu um erro no envio dos dados!" );
			
		})// done
		.fail(function(){
			msgError( "Ocorreu um erro no envio dos dados!" );
		});// fail
	});
	
	carregaTabela();
	
});// $



/* ********************************************************** */
/* *********************** FUNCTIONS ************************ */

function remover(idContato){
	
	alertify.confirm( "Remover", "Deseja remover o contato?", 
	function(){
		alertify.success("H");
	},
	function(){
		alertify.message("Operação cancelada!");
	});
	
}

function adicionar(){
	limpaCampos();
	
	$(".btnAtualizar").hide();
	$(".btnCadastrar").show();
	$('.modal').show();
}

function alterar(idContato){
	
	$.getJSON("buscaContato", {
		id_contato: idContato
	})
	.done(function(data){
		
		if( data.status == "error"){
			msgError("Não foi possivel buscar o contato!");
			return;
		}
		
		$("input[name=nome]").attr("data-id", idContato);
		$("input[name=nome]").val(data.nome);
		$("input[name=email]").val(data.email);
		$("input[name=endereco]").val(data.endereco);
		$("input[name=telefone]").val(data.telefone);
		$("input[name=data_nascimento]").val(data.data);
		
		$(".btnCadastrar").hide();
		$(".btnAtualizar").show();
		
		$('.modal').show();
	})
	.fail(function(){
		msgError("Ocorreu uma falha na busca do contato!");
	});
}

function carregaTabela(){
	
	$.get("listaContatos")
	.done(function(data){
		
		$(".tabela").html(data)
	})
	.fail(function(){
		msgError( "Ocorreu um erro na busca dos dados!" )
	})
}

function validaDados(){
	var nome = $("input[name=nome]").val();
	var email = $("input[name=email]").val();
	var endereco = $("input[name=endereco]").val();
	var telefone = $("input[name=telefone]").val();
	var data = $("input[name=data_nascimento]").val();
	
	if( nome.trim() == "" || nome == typeof("undefined") ) { msgError("O nome deve ser preenchido!");  return false; }
	if( email.trim() == "" || email == typeof("undefined") ) { msgError("O email deve ser preenchido!");  return false; }
	if( endereco.trim() == "" || endereco == typeof("undefined") ) { msgError("O endereco deve ser preenchido!");  return false; }
//	if( telefone.trim() == "" || telefone == typeof("undefined") ) { msgError("O telefone deve ser preenchido!");  return false; }
//	if( data.trim() == "" || data == typeof("undefined") ) { msgError("A data deve ser preenchida!");  return false; }
	
	return true;
}// validaDados

function msgError(mensagem){
	alertify.error(mensagem);
}

function msgSuccess(mensagem){
	alertify.success(mensagem);
}

function limpaCampos(){
	$(".form").each(function(key, value){
		$(this).find("input").val("");
	});
}