package lhos.daw.tarefas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {
	private Connection connection;
	

	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			
			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost/tarefas", "postgres", "aluno");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.connection;
		
	}
	
	
}
