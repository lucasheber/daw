package br.edu.ifsudestemg.barbacena.agendaweb.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

public class BuscaContato implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws HTTPException {
		
		return "lista_contatos.jsp";
	}

}
