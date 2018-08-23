package exercicio3.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exercicio3
 */
@WebServlet("/exercicio3")
public class Exercicio3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exercicio3() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String html = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Servlets</title>\n" + 
				"	<style type=\"text/css\">\n" + 
				"		.exercicios{ width: 900px; display: block; margin: 5% auto;}\n" + 
				"		.exercicios h4{ text-align: center; margin-bottom: 35px; }\n" + 
				"		.exercicios ol, li { padding-top: 2%; }\n" + 
				"	</style>\n" + 
				"</head>\n" + 
				"<body>"
					+ "<div style='width: 400px; top: 60px; margin: 0 auto;'>";
		
		
		Calendar calendar = Calendar.getInstance();
		String out = String.format("%02d/%02d/%d %02d:%02d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, 
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

		html += out + "</div></body></html>";
		
		response.getWriter().print(html);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
