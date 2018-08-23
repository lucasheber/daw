package br.edu.ifsudestemg.barbacena.agendaweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsudestemg.barbacena.agendaweb.dao.ContatoDAO;
import br.edu.ifsudestemg.barbacena.agendaweb.model.Contato;

/**
 * Servlet implementation class ListaContatos
 */
@WebServlet("/listaContatos")
public class ListaContatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaContatos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ContatoDAO contatoDAO = new ContatoDAO();
		List<Contato> contatos = contatoDAO.list();
		
		String tabela = "<table class='table table-striped'>";
			   tabela += "<thead><tr>";
			   tabela += "<th>#</th>";
			   tabela += "<th>NOME</th>";
			   tabela += "<th>EMAIL</th>";
			   tabela += "<th>ENDERECO</th>";
			   tabela += "<th>TELEFONE</th>";
			   tabela += "<th>EDITAR</th>";
			   tabela += "<th>REMOVER</th>";
		   tabela += "</tr></thead><tbody>";
		
		for (Contato contato : contatos) {
			tabela += "<tr>";
				tabela += "<td>" + contato.getId() + "</td>";
				tabela += "<td>" + contato.getNome() + "</td>";
				tabela += "<td>" + contato.getEmail() + "</td>";
				tabela += "<td>" + contato.getEndereco() + "</td>";
				tabela += "<td>" + contato.getTelefone() + "</td>";
				tabela += "<td><i onclick='alterar("+contato.getId()+");' class=\"fas fa-edit editar\"></i></td>";
				tabela += "<td><i onclick='remover("+contato.getId()+");' class=\"fas fa-trash-alt remover\"></i></td>";
			tabela += "</tr>";
		}
		
		tabela += "</tbody></table><br><br><br>";
		
		response.getWriter().print(tabela);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}// ListaContatos
