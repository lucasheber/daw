package br.edu.barbacena.ifsudestemg.daw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.barbacena.ifsudestemg.daw.jdbc.ConnectionFactory;
import br.edu.barbacena.ifsudestemg.daw.modelo.Aluno;

/**
 * Define o Pattern DAO para acesso e manipulacao 
 * dos objetos {@link Aluno} ao Banco de Dados.
 * 
 * @author lucas
 */
public class AlunoDAO {
	
	private Connection connection;
	
	// Nome da tabela de persistencia.
	private final String TABELA = "alunos";
	
	/* **************************************************************************** */
	/* ***************************** PUBLIC METHODS ******************************* */
	
	/**
	 * Cria o objeto DAO para manipulacao e inicia a conexao com o SGBD.
	 */
	public AlunoDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	/**
	 * Insere o aluno na base de dados.
	 */
	public boolean create( Aluno aluno ) {
		
		String query = String.format( "INSERT INTO %s ( nome, email, endereco, datanascimento ) "
								   + "VALUES( ?, ?, ?, ? )", this.TABELA );
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement( query );
			
			preparedStatement.setString( 1, aluno.getNome() );
			preparedStatement.setString( 2, aluno.getEmail() );
			preparedStatement.setString( 3, aluno.getEndereco() );
			preparedStatement.setDate( 4, calendarToDate(aluno.getDataNascimento()) );
			
			preparedStatement.execute();
			
			return true;
			
		} catch (SQLException e) {
			System.out.println( "Ocorreu um erro no create: " + e.getMessage() );
		}
		
		return false;
	}// create
	
	/**
	 * Deleta o aluno passado da base de dados.
	 */
	public boolean delete ( Aluno aluno ) {
		String query = String.format( "DELETE FROM %s WHERE id = ?", this.TABELA );
		
		try {
			PreparedStatement statement = this.connection.prepareStatement( query );
			
			statement.setLong(1, aluno.getId());
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro no delete: " + e.getMessage() );
		}
		
		return false;
	}// delete

	/**
	 * Atualiza as informações do aluno na base de dados.
	 */
	public boolean update( Aluno aluno ) {
		String query = String.format( "UPDATE %s SET nome = ?, email = ?, endereco = ?, datanascimento = ? WHERE id = ?", this.TABELA );
		
		try {
			
			PreparedStatement statement = this.connection.prepareStatement( query );
			
			statement.setString(1, aluno.getNome() );
			statement.setString(2, aluno.getEmail() );
			statement.setString(3, aluno.getEndereco() );
			
			Date date = new Date(aluno.getDataNascimento().getTimeInMillis());
			statement.setDate(4, date );
			
			statement.setLong(5, aluno.getId() );
			
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro no update: " + e.getMessage() );
		}
		
		return false;
	}// update
	
	/**
	 * Busca todos os alunos ordenados pelo matricula.
	 */
	public List<Aluno> retrive(){
		String query = String.format( "SELECT * FROM %s ORDER BY id", this.TABELA );
		
		List<Aluno> alunos = new ArrayList<>();
		
		try {

			PreparedStatement statement = this.connection.prepareStatement( query );
			
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next() ) {
				Aluno aluno = new Aluno();
				
				Calendar date = Calendar.getInstance();
				date.setTime(resultSet.getDate("datanascimento"));
				
				aluno.setDataNascimento( date );
				aluno.setEmail(resultSet.getString("email"));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setEndereco(resultSet.getString("endereco"));
				aluno.setId( resultSet.getLong("id") );
				
				alunos.add(aluno);
			}
			
		} catch (Exception e) { System.out.println( "Ocorreu um erro no retrive: " + e.getMessage() ); }
		
		return alunos;
	}// retrive
	
	/* **************************************************************************** */
	/* ************************ PRIVATE/PROTECTED METHODS ************************* */

	private Date calendarToDate( Calendar data ) {
		long tempoMillis = 0;
		
		tempoMillis = data.getTimeInMillis();
		
		return new Date( tempoMillis );
	}
	
	@Override
	protected void finalize() throws Throwable {
		
		if( connection != null )
			connection.close();
	}// finalize
	
}// class AlunoDAO 
