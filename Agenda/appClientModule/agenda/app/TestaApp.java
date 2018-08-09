package agenda.app;

import java.sql.Connection;

import agenda.dao.Conexao;

public class TestaApp {

	public static void main(String[] args) {
		
		Connection connection = Conexao.getConnection();
		
		if( connection != null )
			System.out.println("Hahaeee!");
		else 
			System.out.println("Opsss...");
		
		Conexao.fechar();
		
//		Contato contato = new Contato();

	}

}
