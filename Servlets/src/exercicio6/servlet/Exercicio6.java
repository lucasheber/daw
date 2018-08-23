package exercicio6.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exercicio6
 */
@WebServlet("/exercicio6")
public class Exercicio6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exercicio6() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double peso = Double.parseDouble(request.getParameter("peso"));
		double altura = Double.parseDouble(request.getParameter("altura"));
		
		double imc = peso / ( altura * altura );
		
		String message = "";
		
		if( imc < 18.5 ) message = "Cuidado! Você está muito abaixo do peso!";
		else if( imc < 25.0 ) message = "Parabéns! Você está em seu peso ideal!";
		else if( imc < 30.0 ) message = "Atenção! Você está acima de seu peso ideal!";
		else if( imc < 35.0 ) message = "Atenção! Obesidade grau 1!";
		else if( imc < 40.0 ) message = "Cuidado! Obesidade grau 2!";
		else message = "Cuidado! Obesidade grau 3";
		
		message = "<script>alert('"+message+"');</script>";
		
		System.out.printf("%f, %f, %f", imc, peso, altura);
		response.getWriter().print(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
