package br.edu.ifsudestemg.barbacena.agendaweb.logica;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import br.edu.ifsudestemg.barbacena.agendaweb.dao.ContatoDAO;
import br.edu.ifsudestemg.barbacena.agendaweb.model.Contato;

public class AdicionaContato implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws HTTPException {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataNascimento = request.getParameter("data_nascimento");
		String telefone = request.getParameter("telefone");
		
		Contato contato = new Contato();
		
		contato.setEmail(email);
		contato.setNome(nome);
		contato.setEndereco(endereco);
		contato.setDataNascimento(ContatoDAO.strDateToCalendar(dataNascimento));
		contato.setTelefone(telefone);
		
		System.out.println(contato);
		
		Connection connection = (Connection) request.getAttribute("connection");
		
		ContatoDAO contatoDAO = new ContatoDAO(connection);

		contatoDAO.insert(contato);
		
		return "adiciona_contato.jsp";
	}

}
