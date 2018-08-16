package br.edu.ifsudestemg.barbacena.agendaweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Connection connection;
	
	private static final String USER_DATABASE = "postgres";
	private static final String PASS_DATABASE = "psql2018*";
	private static final String NAME_DATABASE = "agenda";
	
	private static final String HOST = "localhost";
	
//	private static final String URL_MYSQL = "jdbc:mysql://" + HOST + "/" + NAME_DATABASE;
	private static final String URL_PSQL  = "jdbc:postgresql://" + HOST + "/" + NAME_DATABASE;
	
//	private static final String DRIVER_NAME_MYSQL = "com.mysql.jdbc.Driver";
	private static final String DRIVER_NAME_PSQL  = "com.mysql.jdbc.Driver"; 
	
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
			Class.forName(DRIVER_NAME_PSQL);
			
			// Conecta com o banco de dados
			connection = DriverManager.getConnection(URL_PSQL, USER_DATABASE, PASS_DATABASE);
			
			return connection;
			
		} catch (Exception e) { e.printStackTrace(); }
		
		return null;
	}// getConnection
	
	public static void fechar() {
		
		if( connection == null ) return;
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// fechar
}
