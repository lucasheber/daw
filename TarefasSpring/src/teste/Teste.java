package teste;

import java.sql.Connection;
import java.sql.SQLException;

import lhos.daw.tarefas.jdbc.FactoryConnection;

public class Teste {

	public static void main(String[] args) {
		
		Connection connection = new FactoryConnection().getConnection();
		
		try {
			System.out.println("Yehh! " + connection.getCatalog());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
