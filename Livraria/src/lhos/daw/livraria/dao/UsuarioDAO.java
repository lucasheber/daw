package lhos.daw.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lhos.daw.livraria.dao.connection.ConnectionFactory;
import lhos.daw.livraria.model.Usuario;

public class UsuarioDAO {
	
	private Connection connection;
	
	public UsuarioDAO() {
		connection = ConnectionFactory.getConnection(); 
	}
	
	public boolean adicionar(Usuario usuario) {
		String sql = "INSERT INTO usuario(login, senha, perfil) VALUES (?,?,?);";
		
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getPerfil());
					
			statement.execute();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;	
	}

	public Usuario autenticarUsuario(Usuario usuario) {
		String sql = "SELECT * FROM usuario WHERE login=? AND senha=?;"; 
		Usuario user = null;
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, usuario.getLogin());
			stm.setString(2, usuario.getSenha());
			
			System.out.println(usuario);
			
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				user = new Usuario();
				user.setId(rs.getLong("id"));
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				user.setPerfil(rs.getString("perfil"));
			}
			return user;
		}catch (SQLException e) { 
			return null;
		}
		
	}
	
	public Usuario pesquisa(Long id) {
		
		String sql = "SELECT * FROM usuario WHERE id=?";
		Usuario usuario = new Usuario();
		
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				usuario.setId(rs.getLong("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(rs.getString("perfil"));
			}
			
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return usuario;
	}

	public boolean alterar(Usuario usuario) {
		String sql = "UPDATE usuario SET login=? , senha=? WHERE id=?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, usuario.getLogin());
			stm.setString(2, usuario.getSenha());
			stm.setLong(3, usuario.getId());
			
			return stm.execute();
			
		}catch (SQLException e) {
			return false;
		}
	}

}
