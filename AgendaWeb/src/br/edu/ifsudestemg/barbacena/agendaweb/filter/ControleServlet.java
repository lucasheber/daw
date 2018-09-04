package br.edu.ifsudestemg.barbacena.agendaweb.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsudestemg.barbacena.agendaweb.logica.Logica;

@WebServlet("/controle")
public class ControleServlet extends HttpServlet {
	private final String pack = "br.edu.ifsudestemg.barbacena.agendaweb";
	
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeClasse = pack + ".logica." + request.getParameter("logica");
		
		String url = "index.html";
		
		Class<?> classe;
		
		try {
			classe = Class.forName(nomeClasse);
		
			Logica logica = (Logica) classe.newInstance();
			
			url = logica.executa(request, response);
			
			request.getRequestDispatcher(url).forward(request, response);
			
//			response.getWriter().print("status: \"success\"");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}// service
	
}// ControleServlet
