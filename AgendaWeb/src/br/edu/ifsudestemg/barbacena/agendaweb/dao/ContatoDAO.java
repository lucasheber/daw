package br.edu.ifsudestemg.barbacena.agendaweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.ifsudestemg.barbacena.agendaweb.model.Contato;

public class ContatoDAO {
	
	private Connection conexao = Conexao.getConnection();
	
	public boolean insert( Contato contato ) {
		
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
			
			return true;
			
		} catch (Exception e) {
			return false;
		}		
	}// insert
	
	public boolean remove ( Contato contato ) {
		String query = "DELETE FROM contatos WHERE id = ?";
		
		try {
			PreparedStatement statement = this.conexao.prepareStatement( query );
			
			statement.setLong(1, contato.getId());
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}// remove
	
	public boolean update( Contato contato ) {
		String query = "UPDATE contatos SET nome = ?, email = ?, endereco = ?, telefone = ?, data_nascimento = ? WHERE id = ?";
		
		try {
			
			PreparedStatement statement = this.conexao.prepareStatement( query );
			
			statement.setString(1, contato.getNome() );
			statement.setString(2, contato.getEmail() );
			statement.setString(3, contato.getEndereco() );
			statement.setString(4,  contato.getTelefone());
			
			Date date = new Date(contato.getDataNascimento().getTimeInMillis());
			statement.setDate(5, date );
			
			statement.setLong(6, contato.getId() );
			
			
			statement.execute();
			
			return true;
			
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			return false;
		}
		
	}
	
	public Contato retrive( Long idContato ) {
		Contato contato = null;
		
		try {
			String query = "SELECT * FROM contatos WHERE id = ?";
			
			this.conexao.prepareStatement( query );
			
			PreparedStatement statement = this.conexao.prepareStatement( query );
			
			statement.setLong(1, idContato );
			
			ResultSet resultSet = statement.executeQuery();
			
			// Verifica se existe resultado
			if( !resultSet.next() ) return null;
			
			Calendar date = Calendar.getInstance();
			date.setTime(resultSet.getDate("data_nascimento"));
			
			contato = new Contato();
			
			contato.setDataNascimento( date );
			contato.setEmail(resultSet.getString("email"));
			contato.setNome(resultSet.getString("nome"));
			contato.setEndereco(resultSet.getString("endereco"));
			contato.setTelefone(resultSet.getString("telefone"));
			contato.setId(resultSet.getLong("id"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contato;
	}// retrive
	
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
	
	/**
	 * Retorna a data no formato DD/MM/YYYY
	 * 
	 * @param data <code>Calendar</code> a data a ser convertida.
	 * 
	 * @return <code>String</code> da data convertida.
	 */
	public static String dateFormatBR( Calendar data ) {
		
		String dataBR = "";
		dataBR += String.format("%02d", data.get(Calendar.DAY_OF_MONTH) );
		dataBR += "/" +  String.format("%02d", data.get(Calendar.MONTH) + 1);
		dataBR += "/" +  data.get(Calendar.YEAR);
		
		return dataBR;
	}
	
	/**
	 * Converte um data no formato DD/MM/YYYY para <code>Calendar</code>.
	 * 
	 * @param data a data no formato DD/MM/YYYY.
	 * 
	 * @return <code>Calendar</code> se converteu com sucesso, <code>null</code> se
	 * a data passada não for uma data no fomato válido.
	 */
	public static Calendar strDateToCalendar( String data ) {
		
		if( !isValidDate(data) ) return null;
//		System.out.println(data);
		
		String[] campos = data.split("/");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(campos[2]), Integer.parseInt(campos[1]) - 1, Integer.parseInt(campos[0]));
		
		return calendar;
	}
	
	/**
	 * Verifica se uma data está no formato DD/MM/YYYY.
	 * 
	 * @param data a data a ser verificada.
	 * 
	 * @return <code>true</code> se for válida, <code>se</code> não for válida.
	 */
	public static boolean isValidDate(String data) {
		String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		
		boolean isValid = matcher.matches();
		
		if( isValid ) return isValid;
			
		return false;
	}
	
}// ContatoDAO
