package br.edu.ifsudestemg.barbacena.agendaweb.logica;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import br.edu.ifsudestemg.barbacena.agendaweb.dao.ContatoDAO;

public class RemoverContato implements Logica{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws HTTPException {
		
//		System.out.println(request.getParameter("logica"));
		Long id = Long.parseLong(request.getParameter("id_contato"));
		
		Connection connection = (Connection) request.getAttribute("connection");
		
		ContatoDAO contatoDAO = new ContatoDAO(connection);
		
		contatoDAO.remove(contatoDAO.retrive(id));
		
		return "lista_contatos.jsp";
	} 

}
