package br.edu.barbacena.ifsudestemg.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.barbacena.ifsudestemg.daw.jdbc.ConnectionFactory;
import br.edu.barbacena.ifsudestemg.daw.modelo.Professor;

/**
 * Define o Pattern DAO para acesso e manipulacao 
 * dos objetos {@link Professor} ao Banco de Dados.
 * 
 * @author lucas
 */
public class ProfessorDAO {
	
	private Connection connection;
	
	// Nome da tabela de persistencia.
	private final String TABELA = "professores";
	
	/* **************************************************************************** */
	/* ***************************** PUBLIC METHODS ******************************* */
	
	/**
	 * Cria o objeto DAO para manipulacao e inicia a conexao com o SGBD.
	 */
	public ProfessorDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	/**
	 * Insere o professor na base de dados.
	 */
	public boolean create( Professor professor ) {
		
		String query = String.format( "INSERT INTO %s ( nome, email, grau_formacao ) "
								   + "VALUES( ?, ?, ? )", this.TABELA );
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement( query );
			
			preparedStatement.setString( 1, professor.getNome() );
			preparedStatement.setString( 2, professor.getEmail() );
			preparedStatement.setString( 3, professor.getGrauFormacao() );

			preparedStatement.execute();
			
			return true;
			
		} catch (SQLException e) {
			System.out.println( "Ocorreu um erro no create: " + e.getMessage() );
		}
		
		return false;
	}// create
	
	/**
	 * Deleta o professor passado da base de dados.
	 */
	public boolean delete ( Professor professor ) {
		String query = String.format( "DELETE FROM %s WHERE id = ?", this.TABELA );
		
		try {
			PreparedStatement statement = this.connection.prepareStatement( query );
			
			statement.setLong(1, professor.getId());
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro no delete: " + e.getMessage() );
		}
		
		return false;
	}// delete

	/**
	 * Atualiza as informações do professor na base de dados.
	 */
	public boolean update( Professor professor ) {
		String query = String.format( "UPDATE %s SET nome = ?, email = ?, grau_formacao = ? WHERE id = ?", this.TABELA );
		
		try {
			
			PreparedStatement statement = this.connection.prepareStatement( query );
			
			statement.setString(1, professor.getNome() );
			statement.setString(2, professor.getEmail() );
			statement.setString(3, professor.getGrauFormacao() );
			statement.setLong(4, professor.getId() );
					
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro no update: " + e.getMessage() );
		}
		
		return false;
	}// update
	
	/**
	 * Busca todos os professores ordenados pelo matricula.
	 */
	public List<Professor> retrive(){
		String query = String.format( "SELECT * FROM %s ORDER BY id", this.TABELA );
		
		List<Professor> professores = new ArrayList<>();
		
		try {

			PreparedStatement statement = this.connection.prepareStatement( query );
			
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next() ) {
				Professor professor = new Professor();
				
				professor.setEmail(resultSet.getString("email"));
				professor.setNome(resultSet.getString("nome"));
				professor.setGrauFormacao(resultSet.getString("grau_formacao"));
				professor.setId( resultSet.getLong("id") );
				
				professores.add(professor);
			}
			
		} catch (Exception e) { System.out.println( "Ocorreu um erro no retrive: " + e.getMessage() ); }
		
		return professores;
	}// retrive
	
	/* **************************************************************************** */
	/* ************************ PRIVATE/PROTECTED METHODS ************************* */
	
	@Override
	protected void finalize() throws Throwable {
		
		if( connection != null )
			connection.close();
	}// finalize
	
}// class AlunoDAO 
