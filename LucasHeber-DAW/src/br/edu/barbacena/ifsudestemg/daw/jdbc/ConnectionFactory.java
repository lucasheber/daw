package br.edu.barbacena.ifsudestemg.daw.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Realiza a conexao com o SGBD.
 * 
 * @author Lucas Heber
 */
public class ConnectionFactory  {
	private static Connection connection;
	
	private static final String USER_DATABASE = "postgres";
	private static final String PASS_DATABASE = "psql2018*";
	private static final String NAME_DATABASE = "daw";
	
	private static final String HOST = "localhost:5432";
	
	private static final String URL = "jdbc:postgresql://" + HOST + "/" + NAME_DATABASE;
	private static final String DRIVER_NAME = "org.postgresql.Driver"; 
	
	/**
	 * Realiza a conexao com o banco de dados.
	 * 
	 * @return {@link Connection} a conexao estabelecida.
	 */
	public static Connection getConnection() {
		try {
			
			// Verifica se ja existe uma conexao aberta
			if( connection != null ) return connection;
			
			// Carregando o JDBC Driver padrão
			Class.forName(DRIVER_NAME);
			
			// Conecta com o banco de dados
			connection = DriverManager.getConnection(URL, USER_DATABASE, PASS_DATABASE);
			
			return connection;
			
		} catch (Exception e) { e.printStackTrace(); }
		
		return null;
	}// getConnection
	
}// class ConnectionFactory
