package exercicio1.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exercicio1
 */
@WebServlet("/exercicio1")
public class Exercicio1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exercicio1() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tabela = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Exercicio 1</title>\n" + 
				"</head>\n" + 
				"<body>";
		
		tabela += "<table style='border-collapse: collapse; width: 900px;' border='1'>";
			tabela += "<thead><tr>";
			
		Enumeration<String> enumeration = request.getParameterNames();
		
		String body = "";
		while( enumeration.hasMoreElements() ) {
			String header = enumeration.nextElement();
			
			tabela += "<th>" + header + "</th>";
			
			String[] enumerationH = request.getParameterValues(header);
			
			body += "<td>";
			
			for( String value : enumerationH )
				body += value + "<br>";
			
			body += "</td>";
		}
		
		tabela += "</tr></thead>";
		
		tabela += "<tbody><tr>" + body + "</tr></tbody>";
		
		tabela += "</table>";
		
		response.getWriter().print(tabela);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
