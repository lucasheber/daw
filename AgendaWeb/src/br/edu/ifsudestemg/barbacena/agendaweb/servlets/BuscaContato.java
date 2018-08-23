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
 * Servlet implementation class BuscaContato
 */
@WebServlet("/buscaContato")
public class BuscaContato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaContato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id_contato"));
		
		ContatoDAO contatoDAO = new ContatoDAO();
		Contato contato = contatoDAO.retrive(id);
		
		String jsonRetorno;
		
		if( contato == null ) {
			jsonRetorno = "{'status': 'error'}";
			jsonRetorno =  jsonRetorno.replaceAll("'","\"");
			response.getWriter().println(jsonRetorno);
		}
		else {
			jsonRetorno = "{'status': 'success', 'id': %d, 'nome': '%s', 'endereco': '%s', 'telefone': '%s', 'email': '%s', 'data': '%s'}";
		
			jsonRetorno = jsonRetorno.replaceAll("'","\"");
		
			response.getWriter().printf(jsonRetorno, contato.getId(), contato.getNome(), contato.getEndereco(), 
								contato.getTelefone(), contato.getEmail(), ContatoDAO.dateFormatBR(contato.getDataNascimento()));
		}
	}// doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
