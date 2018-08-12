package br.edu.barbacena.ifsudestemg.daw.teste;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.barbacena.ifsudestemg.daw.jdbc.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] args) {
		testeConexao();
	}// main

	public static void testeConexao() {
		// Abre a conexao com o SGBD
		Connection connection = ConnectionFactory.getConnection();
		
		// Verifica se a conexao foi estabelecida.
		if( connection != null )
			System.out.println( "Conexão aberta!" );
		else 
			System.out.println( "A conexão nao foi estabelecida!" );
		
		// Trata e fecha a conexao.
		try { connection.close();
		} catch (SQLException e) { System.out.println( "Error: " + e.getMessage() ); }
		
	}// testeConexao
	
}// class TestaConexao
