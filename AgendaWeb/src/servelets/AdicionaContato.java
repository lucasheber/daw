package servelets;

import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		contato.setDataNascimento(strDateToCalendar(dataNascimento));
		contato.setTelefone(telefone);
		
		ContatoDAO contatoDAO = new ContatoDAO();
		contatoDAO.insert(contato);
		
		response.getWriter().print("{ \"status\": \"success\"}");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * Converte um data no formato DD/MM/YYYY para <code>Calendar</code>.
	 * 
	 * @param data a data no formato DD/MM/YYYY.
	 * 
	 * @return <code>Calendar</code> se converteu com sucesso, <code>null</code> se
	 * a data passada não for uma data no fomato válido.
	 */
	private static Calendar strDateToCalendar( String data ) {
		
		if( !isValidDate(data) ) return null;
//		System.out.println(data);
		
		String[] campos = data.split("/");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(campos[2]), Integer.parseInt(campos[1]) - 1, Integer.parseInt(campos[0]));
		
		return calendar;
	}
	
	/**
	 * Verifica se uma data está no formato DD/MM/YYYY.
	 * 
	 * @param data a data a ser verificada.
	 * 
	 * @return <code>true</code> se for válida, <code>se</code> não for válida.
	 */
	public static boolean isValidDate(String data) {
		String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		
		boolean isValid = matcher.matches();
		
		if( isValid ) return isValid;
			
		return false;
	}

}
