package lhos.daw.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lhos.daw.tarefas.jdbc.FactoryConnection;
import lhos.daw.tarefas.model.Tarefa;

public class TarefaDAO {
	private Connection connection;
	private final String TABLE = "tarefas";
	
	public TarefaDAO() {
		this.connection = new FactoryConnection().getConnection();
	}
	
	public boolean insert(Tarefa tarefa) {
		
		String query = "INSERT INTO %s ( descricao ) VALUES ( ? )";
		query = String.format(query, TABLE);
		
		try {
			
			PreparedStatement statement = this.connection.prepareStatement( query );
			
			statement.setString(1, tarefa.getDescricao() );
			
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}// insert
	
	public boolean remove (Tarefa contato) {
		String query = "DELETE FROM %s WHERE id = ?";
		query = String.format(query, TABLE);
		
		try {
			PreparedStatement statement = this.connection.prepareStatement( query );
			
			statement.setLong(1, contato.getId());
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}// remove
	
	public boolean update(Tarefa tarefa) {
		String query = "UPDATE %s SET descricao = ?, datafinalizacao = ?, finalizado = ?, WHERE id = ?";
		query = String.format(query, TABLE);
		
		try {
			
			PreparedStatement statement = this.connection.prepareStatement( query );
			
			statement.setString(1, tarefa.getDescricao() );
			statement.setDate(2,  new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			statement.setBoolean(3, tarefa.isFinalizado());
			statement.setLong(4, tarefa.getId() );
			
			
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			return false;
		}
		
	}// update
	
	public Tarefa retrive(Long idTarefa) {
		Tarefa contato = null;
		
		try {
			String query = "SELECT * FROM %s WHERE id = ?";
			query = String.format(query, TABLE);
			
			this.connection.prepareStatement( query );
			
			PreparedStatement statement = this.connection.prepareStatement( query );
			
			statement.setLong(1, idTarefa );
			
			ResultSet resultSet = statement.executeQuery();
			
			// Verifica se existe resultado
			if( !resultSet.next() ) return null;

			Tarefa tarefa = new Tarefa();
			
			tarefa.setDescricao(resultSet.getString("descricao"));
			tarefa.setFinalizado(resultSet.getBoolean("finalizado"));
			tarefa.setId(resultSet.getLong("id"));
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(resultSet.getDate("datafinalizacao"));

			tarefa.setDataFinalizacao(calendar);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contato;
	}// retrive
	
	public List<Tarefa> taskList(){
		String query = "SELECT * FROM %s ORDER BY id";
		query = String.format(query, TABLE);
		
		List<Tarefa> tarefas = new ArrayList<>();
		
		try {
			this.connection.prepareStatement( query );
			
			PreparedStatement statement = this.connection.prepareStatement( query );
			
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next() ) {
				Tarefa tarefa = new Tarefa();
				
				tarefa.setDescricao(resultSet.getString("descricao"));
				tarefa.setFinalizado(resultSet.getBoolean("finalizado"));
				tarefa.setId(resultSet.getLong("id"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new java.util.Date());

				tarefa.setDataFinalizacao(calendar);
				
				tarefas.add(tarefa);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tarefas;
	}
	
}
