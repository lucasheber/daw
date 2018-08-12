package br.edu.barbacena.ifsudestemg.daw.teste;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import br.edu.barbacena.ifsudestemg.daw.dao.AlunoDAO;
import br.edu.barbacena.ifsudestemg.daw.modelo.Aluno;

public class AlunoTeste {
	private AlunoDAO alunoDAO = new AlunoDAO();
	
	private final static String NOME_PROGRAMA = "Cadastro de Aluno";

	public static void main(String[] args) {
		UIManager.put("OptionPane.okButtonText", "Continuar");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		new AlunoTeste().menu();
		
		System.exit(0);
	}

	public boolean cadastrar() {
		Aluno aluno = lerDados();
		
		if( aluno == null ) {  return msgOperacaoCancelada(); }

		if( alunoDAO.create(aluno) ) showMessageDialog(null, "Aluno cadastrado com sucesso!", NOME_PROGRAMA, INFORMATION_MESSAGE);
		
		return true;
	}// cadastrar
	
	public void buscar() {
		
		List<Aluno> alunos = alunoDAO.retrive();
		String texto = "ALUNOS CADASTRADOS:\n\n";
		
		for (Aluno aluno : alunos) {
			texto += String.format( "%04d. %s, %s, %s, %s\n", aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getEndereco(), AlunoTeste.dateFormatBR(aluno.getDataNascimento()) );
		}
		
		showMessageDialog(null, texto + "\n\n", NOME_PROGRAMA, INFORMATION_MESSAGE);
	}
	
	public boolean atualizar() {
		
		List<Aluno> alunos = alunoDAO.retrive();
		
		Object objeto = showInputDialog(null, "Escolha um aluno:\n", NOME_PROGRAMA, QUESTION_MESSAGE, null, alunos.toArray(), 0);
		
		if( objeto instanceof Aluno ) {
			Aluno aluno = (Aluno) objeto;
			
			Object nome = showInputDialog(null, "Nome do aluno: ", NOME_PROGRAMA, QUESTION_MESSAGE, null, null, aluno.getNome());
			if( nome == null || nome.toString().trim().isEmpty() ) return msgOperacaoCancelada();
			
			Object email = showInputDialog(null, "E-mail do aluno: ", NOME_PROGRAMA, QUESTION_MESSAGE, null, null, aluno.getEmail());
			if( email == null || email.toString().trim().isEmpty() ) return msgOperacaoCancelada();
			
			Object endereco = showInputDialog(null, "Endereco do aluno: ", NOME_PROGRAMA, QUESTION_MESSAGE, null, null, aluno.getEndereco());
			if( endereco == null || endereco.toString().trim().isEmpty() ) return msgOperacaoCancelada();

			Object data = showInputDialog(null, "Data de nascimento do aluno: ", NOME_PROGRAMA, QUESTION_MESSAGE, null, null, aluno.getDataNascimento());
			if( data == null || data.toString().trim().isEmpty() || !isValidDate(data.toString())) return msgOperacaoCancelada();

			aluno.setNome(nome.toString());
			aluno.setEmail(email.toString());
			aluno.setEndereco(endereco.toString());
			aluno.setDataNascimento(AlunoTeste.strDateToCalendar(data.toString()));
			
			if( alunoDAO.update(aluno) ) { showMessageDialog(null, "Aluno atualizado com sucesso!", NOME_PROGRAMA, INFORMATION_MESSAGE); return true; }
		}
		
		return msgOperacaoCancelada();
	}// atualizar
	
	public boolean delete() {
		List<Aluno> alunos = alunoDAO.retrive();
		
		Object objeto = showInputDialog(null, "Escolha um aluno: ", NOME_PROGRAMA, QUESTION_MESSAGE, null, alunos.toArray(), 0);
		
		if( objeto instanceof Aluno ) {
			Aluno aluno = (Aluno) objeto;
			
			String mensagem = "Deseja excluir o aluno abaixo?\n\n" + String.format( "%04d. %s, %s, %s, %s\n", aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getEndereco(), AlunoTeste.dateFormatBR(aluno.getDataNascimento()) );
			mensagem += "\n\n";
			
			String opcoes[] = { "EXCLUIR", "CANCELAR"};

			int opcao = JOptionPane.showOptionDialog(null, mensagem, NOME_PROGRAMA, JOptionPane.DEFAULT_OPTION, QUESTION_MESSAGE, null, opcoes, 0);
			
			if( opcao == 0 ) 
				if( alunoDAO.delete(aluno) ) { showMessageDialog(null, "Aluno excluido com sucesso!", NOME_PROGRAMA, INFORMATION_MESSAGE); return true; };
			
		}
		
		return msgOperacaoCancelada();
	}// delete
	
	public void menu() {
		String opcoes[] = { "CADASTRAR", "BUSCAR", "ATUALIZAR", "REMOVER" };
		
		int opcao;
		
		do { opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:", NOME_PROGRAMA, JOptionPane.DEFAULT_OPTION, QUESTION_MESSAGE, null, opcoes, 0);
			
			switch ( opcao ) {
				case 0: cadastrar(); break;
				case 1: buscar(); break;
				case 2: atualizar(); break;
				case 3: delete(); break;

			default:
				break;
			}
		} while ( opcao != JOptionPane.CLOSED_OPTION );
	}// menu
	
	/* **************************************************************************** */
	/* ***************************** PRIVATE METHODS ****************************** */

	
	private boolean msgOperacaoCancelada() {
		
		showMessageDialog(null, "Operação cancelada!", NOME_PROGRAMA, INFORMATION_MESSAGE);
		
		return false;
	}// msgOperacaoCancelada
	
	private Aluno lerDados() {
		Aluno aluno = null;
		
		String nome = (String) showInputDialog(null, "Nome do aluno: ", NOME_PROGRAMA, QUESTION_MESSAGE );
		if( nome == null || nome.trim().isEmpty() ) return null;
		
		String email = showInputDialog(null, "E-mail do aluno: ", NOME_PROGRAMA, QUESTION_MESSAGE );
		if( email == null || email.trim().isEmpty() ) return null;
		
		String endereco = showInputDialog(null, "Endreço do aluno: ", NOME_PROGRAMA, QUESTION_MESSAGE );
		if( endereco == null || endereco.trim().isEmpty() ) return null;
		
		String data = showInputDialog(null, "Data de nascimento do aluno: ", NOME_PROGRAMA, QUESTION_MESSAGE );
		if( data == null || data.trim().isEmpty() || !isValidDate(data) ) return null;
		
		aluno = new Aluno();
		
		aluno.setNome(nome);
		aluno.setEmail(email);
		aluno.setEndereco(endereco);
		aluno.setDataNascimento(AlunoTeste.strDateToCalendar(data));
		
		return aluno;
	}// lerDados
	
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
	private static boolean isValidDate(String data) {
		String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		
		boolean isValid = matcher.matches();
		
		if( isValid ) return isValid;
		
		showMessageDialog( null, "A data informada não é válida!", NOME_PROGRAMA, JOptionPane.ERROR_MESSAGE, null );
		
		return false;
	}
	
	/**
	 * Retorna a data no formato DD/MM/YYYY
	 * 
	 * @param data <code>Calendar</code> a data a ser convertida.
	 * 
	 * @return <code>String</code> da data convertida.
	 */
	private static String dateFormatBR( Calendar data ) {
		
		String dataBR = "";
		dataBR += String.format("%02d", data.get(Calendar.DAY_OF_MONTH) );
		dataBR += "/" +  String.format("%02d", data.get(Calendar.MONTH) + 1);
		dataBR += "/" +  data.get(Calendar.YEAR);
		
		return dataBR;
	}
}
