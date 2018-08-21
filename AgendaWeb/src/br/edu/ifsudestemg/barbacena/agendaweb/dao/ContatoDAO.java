package br.edu.ifsudestemg.barbacena.agendaweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ifsudestemg.barbacena.agendaweb.model.Contato;

public class ContatoDAO {
	
	private Connection conexao = Conexao.getConnection();
	
	public void insert( Contato contato ) {
		
		String query = "INSERT INTO contatos ( nome, email, endereco, telefone, data_nascimento ) "
				     + "VALUES ( ?, ?, ?, ?, ?)";
	
		try {
			
			PreparedStatement statement = this.conexao.prepareStatement( query );
			
			statement.setString(1, contato.getNome() );
			statement.setString(2, contato.getEmail() );
			statement.setString(3, contato.getEndereco() );
			statement.setString(4,  contato.getTelefone());
			
			Date date = new Date( contato.getDataNascimento().getTimeInMillis() );
			statement.setDate( 5, date );
			
			statement.execute();			
		} catch (Exception e) {
			// TODO: handle exception
		}
					
	}
	
	public void remove ( Contato contato ) {
		String query = "DELETE FROM contatos WHERE id = ?";
		
		try {
			PreparedStatement statement = this.conexao.prepareStatement( query );
			
			statement.setLong(1, contato.getId());
			statement.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// remove
	
	public void update( Contato contato ) {
		String query = "UDPATE contatos SET nome = ?, email = ?, edereco = ?, telefone = ? data_nascimento = ? WHERE id = ?";
		
		try {
			
			PreparedStatement statement = this.conexao.prepareStatement( query );
			
			statement.setString(1, contato.getNome() );
			statement.setString(2, contato.getEmail() );
			statement.setString(3, contato.getEndereco() );
			statement.setString(4,  contato.getTelefone());
			
			Date date = new Date(contato.getDataNascimento().getTimeInMillis());
			statement.setDate(5, date );
			
			statement.setLong(6, contato.getId() );
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public List<Contato> list(){
		String query = "SELECT * FROM contatos ORDER BY id";
		List<Contato> contatos = new ArrayList<>();
		
		try {
			this.conexao.prepareStatement( query );
			
			PreparedStatement statement = this.conexao.prepareStatement( query );
			
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next() ) {
				Contato contato = new Contato();
				
				Calendar date = Calendar.getInstance();
				date.setTime(resultSet.getDate("data_nascimento"));
				
				contato.setDataNascimento( date );
				contato.setEmail(resultSet.getString("email"));
				contato.setNome(resultSet.getString("nome"));
				contato.setEndereco(resultSet.getString("endereco"));
				contato.setTelefone(resultSet.getString("telefone"));
				contato.setId(resultSet.getLong("id"));
				
				contatos.add(contato);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contatos;
	}
	
	
}// ContatoDAO
