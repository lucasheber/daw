package br.edu.ifsudestemg.barbacena.agendaweb.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

public interface Logica {
	public String executa( HttpServletRequest request, HttpServletResponse response) throws HTTPException;
}
