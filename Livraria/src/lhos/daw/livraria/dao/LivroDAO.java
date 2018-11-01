package lhos.daw.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lhos.daw.livraria.dao.connection.ConnectionFactory;
import lhos.daw.livraria.model.Livro;

public class LivroDAO {
	
	private Connection connection;

	public LivroDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public boolean adiciona(Livro livro) {
		String sql = "INSERT INTO livro(titulo, autor, descricao, categoria, valor, quant) VALUES (?,?,?,?,?,?);";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, livro.getTitulo());
			statement.setString(2, livro.getAutor());
			statement.setString(3, livro.getDescricao());
			statement.setString(4, livro.getCategoria());
			statement.setDouble(5, livro.getValor());
			statement.setInt(6, livro.getQuantidade());
					
			statement.execute();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;	
	}
	
	public boolean altera(Livro livro) {
		if(livro.getId() == null) throw new IllegalStateException("Id do livro não deve ser null");
		
		String sql = "UPDATE livro SET titulo=?, autor=?, descricao=?, categoria=?, valor=?, quant=? where id=?;";

		PreparedStatement statement;
		try { 
			statement = connection.prepareStatement(sql);
			statement.setString(1, livro.getTitulo());
			statement.setString(2, livro.getAutor());
			statement.setString(3, livro.getDescricao());
			statement.setString(4, livro.getCategoria());
			statement.setDouble(5, livro.getValor());
			statement.setInt(6, livro.getQuantidade());
			statement.setLong(7, livro.getId());
			
			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean remove(Long id) {
		if(id == null) throw new IllegalStateException("Id do livro não deve ser null");
				
		String sql = "DELETE FROM livro WHERE id=?;";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.execute();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public Livro pesquisa(Long id) {
		if(id == null) throw new IllegalStateException("Id do livro não deve ser null");
		
		String sql = "SELECT * FROM livro WHERE id=?";
		Livro livro = new Livro();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				livro.setId(rs.getLong("id"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setValor(rs.getFloat("valor"));
				livro.setQuantidade(rs.getInt("quant"));
			}
			
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return livro;
	}
	
	
	public List<Livro> listaTodos(){
		String sql = "SELECT * FROM livro;";
		List<Livro> livros = new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getLong("id"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setValor(rs.getFloat("valor"));
				livro.setQuantidade(rs.getInt("quant"));
				livros.add(livro);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return livros;
		
	}

	public List<Livro> pesquisarPorTitulo(String titulo) {
		String sql = "SELECT * FROM livro WHERE titulo ILIKE ?;";
		List<Livro> livros = new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, '%'+titulo+'%');
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getLong("id"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setValor(rs.getFloat("valor"));
				livro.setQuantidade(rs.getInt("quant"));
				livros.add(livro);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return livros;
	}

	public List<String> listarCategorias() {
		String sql = "SELECT DISTINCT categoria FROM livro";
		List<String> categorias = new ArrayList<>();
			
		try(PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery()){
			
			while(rs.next()) 
				categorias.add(rs.getString("categoria"));

		}catch (SQLException e) { }
		
		return categorias;
	}
	
	public List<Livro> listaTodosPorCategoria(String categoria){
		String sql = "SELECT * FROM livro WHERE categoria=?;";
		List<Livro> livros = new ArrayList<>();
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, categoria);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getLong("id"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setValor(rs.getFloat("valor"));
				livro.setQuantidade(rs.getInt("quant"));
				livros.add(livro);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return livros;
		
	}

	public boolean subtrairLivros(Long id, int quant) {
		String sql = "UPDATE livro SET quant=? where id=?;";
		
		Livro livro = pesquisa(id);
		if(livro == null) return false;
		
		try(PreparedStatement statement = connection.prepareStatement(sql);){ 
			statement.setInt(1, livro.getQuantidade() - quant);
			statement.setLong(2, id);
		
			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public int obterQuantidadeLivros(Long id) {
		String sql = "SELECT quant FROM livro WHERE id=?;";
		
		try(PreparedStatement statement = connection.prepareStatement(sql);){ 
			statement.setLong(1, id);
			
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				return rs.getInt("quant");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
}
