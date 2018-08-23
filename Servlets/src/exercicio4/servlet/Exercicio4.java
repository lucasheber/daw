package exercicio4.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exercicio4
 */
@WebServlet("/exercicio4")
public class Exercicio4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exercicio4() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String number = "10", requestStr;
		Integer numberInt;
		
		requestStr = request.getParameter("maximo");
		
		if( requestStr != null )
				number = requestStr;
		
		numberInt = Integer.parseInt(number);
		
		String tabela = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Exercicio 4</title>\n" + 
				"</head>\n" + 
				"<body>";
		
		tabela += "<table style='border-collapse: collapse;' border='1'>";
			tabela += "<thead>";
			
			for( int i = 0; i <= numberInt; i++ ) {
				tabela += "<tr>";
					tabela += "<th>" + i + "</th>";
					tabela += "<td>" + fatorial(i) + "</td>";
				tabela += "</tr>";
			}
			
		response.getWriter().print(tabela);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private int fatorial( int number ) {
		int fat = 1;
		
		for( int i = 2; i <= number; i++ )
		     fat *= i;
		
		return fat;
	}

}
