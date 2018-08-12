package br.edu.barbacena.ifsudestemg.daw.teste;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import br.edu.barbacena.ifsudestemg.daw.dao.ProfessorDAO;
import br.edu.barbacena.ifsudestemg.daw.modelo.Professor;

public class ProfessorTeste {
	private ProfessorDAO professorDAO = new ProfessorDAO();
	
	private final static String NOME_PROGRAMA = "Cadastro de Professores";

	public static void main(String[] args) {
		UIManager.put("OptionPane.okButtonText", "Continuar");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		new ProfessorTeste().menu();
		
		System.exit(0);
	}

	public boolean cadastrar() {
		Professor professor = lerDados();
		
		if( professor == null ) {  return msgOperacaoCancelada(); }

		if( professorDAO.create(professor) ) showMessageDialog(null, "Professor cadastrado com sucesso!", NOME_PROGRAMA, INFORMATION_MESSAGE);
		
		return true;
	}// cadastrar
	
	public void buscar() {
		
		List<Professor> professores = professorDAO.retrive();
		String texto = "PROFESSORES CADASTRADOS:\n\n";
		
		for (Professor professor : professores) {
			texto += String.format( "%04d. %s, %s, %s\n", professor.getId(), professor.getNome(), professor.getEmail(), professor.getGrauFormacao() );
		}
		
		showMessageDialog(null, texto + "\n\n", NOME_PROGRAMA, INFORMATION_MESSAGE);
	}
	
	public boolean atualizar() {
		
		List<Professor> professores = professorDAO.retrive();
		
		Object objeto = showInputDialog(null, "Escolha um professor:\n", NOME_PROGRAMA, QUESTION_MESSAGE, null, professores.toArray(), 0);
		
		if( objeto instanceof Professor ) {
			Professor professor = (Professor) objeto;
			
			Object nome = showInputDialog(null, "Nome do professor: ", NOME_PROGRAMA, QUESTION_MESSAGE, null, null, professor.getNome());
			if( nome == null || nome.toString().trim().isEmpty() ) return msgOperacaoCancelada();
			
			Object email = showInputDialog(null, "E-mail do professor: ", NOME_PROGRAMA, QUESTION_MESSAGE, null, null, professor.getEmail() );
			if( email == null || email.toString().trim().isEmpty() ) return msgOperacaoCancelada();
			
			Object formacao = showInputDialog(null, "Grau do professor: ", NOME_PROGRAMA, QUESTION_MESSAGE, null, null, professor.getGrauFormacao() );
			if( formacao == null || formacao.toString().trim().isEmpty() ) return msgOperacaoCancelada();


			professor.setNome(nome.toString());
			professor.setEmail(email.toString());
			professor.setGrauFormacao(formacao.toString());
			
			if( professorDAO.update(professor) ) { showMessageDialog(null, "Professor atualizado com sucesso!", NOME_PROGRAMA, INFORMATION_MESSAGE); return true; }
		}
		
		return msgOperacaoCancelada();
	}// atualizar
	
	public boolean delete() {
		List<Professor> professores = professorDAO.retrive();
		
		Object objeto = showInputDialog(null, "Escolha um professor: ", NOME_PROGRAMA, QUESTION_MESSAGE, null, professores.toArray(), 0);
		
		if( objeto instanceof Professor ) {
			Professor professor = (Professor) objeto;
			
			String mensagem = "Deseja excluir o professor abaixo?\n\n" + String.format( "%04d. %s, %s, %s\n", professor.getId(), professor.getNome(), professor.getEmail(), professor.getGrauFormacao() );
			mensagem += "\n\n";
			
			String opcoes[] = { "EXCLUIR", "CANCELAR"};

			int opcao = JOptionPane.showOptionDialog(null, mensagem, NOME_PROGRAMA, JOptionPane.DEFAULT_OPTION, QUESTION_MESSAGE, null, opcoes, 0);
			
			if( opcao == 0 ) 
				if( professorDAO.delete(professor) ) { showMessageDialog(null, "Professor excluido com sucesso!", NOME_PROGRAMA, INFORMATION_MESSAGE); return true; };
			
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
	
	private Professor lerDados() {
		Professor professor = null;
		
		String nome = (String) showInputDialog(null, "Nome do professor: ", NOME_PROGRAMA, QUESTION_MESSAGE );
		if( nome == null || nome.trim().isEmpty() ) return null;
		
		String email = showInputDialog(null, "E-mail do professor: ", NOME_PROGRAMA, QUESTION_MESSAGE );
		if( email == null || email.trim().isEmpty() ) return null;
		
		String formacao = showInputDialog(null, "Grau do professor: ", NOME_PROGRAMA, QUESTION_MESSAGE );
		if( formacao == null || formacao.trim().isEmpty() ) return null;
		
		professor = new Professor();
		
		professor.setNome(nome);
		professor.setEmail(email);
		professor.setGrauFormacao(formacao);
		
		return professor;
	}// lerDados
}
