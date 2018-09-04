package br.edu.ifsudestemg.barbacena.agendaweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsudestemg.barbacena.agendaweb.dao.ContatoDAO;
import br.edu.ifsudestemg.barbacena.agendaweb.model.Contato;

/**
 * Servlet implementation class AdicionaContato
 */
@WebServlet("/adicionaContato")
public class AdicionaContato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionaContato() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
//		ContatoDAO contatoDAO = new ContatoDAO();
		
//		if( contatoDAO.insert(contato) )
//			response.getWriter().print("{ \"status\": \"success\"}");
//		else 
//			response.getWriter().print("{ \"status\": \"error\"}");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
