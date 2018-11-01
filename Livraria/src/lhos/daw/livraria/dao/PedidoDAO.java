package lhos.daw.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lhos.daw.livraria.dao.connection.ConnectionFactory;
import lhos.daw.livraria.model.Item;
import lhos.daw.livraria.model.Pedido;
import lhos.daw.livraria.model.Usuario;

public class PedidoDAO {
	
	private Connection connection;
	private ItemDAO itemDAO;
	private UsuarioDAO usuarioDAO;
	private LivroDAO livroDAO;

	public PedidoDAO () {
		this.connection = ConnectionFactory.getConnection();
		itemDAO = new ItemDAO();
		usuarioDAO = new UsuarioDAO();
		livroDAO = new LivroDAO();
	}
	
	public boolean adiciona(Pedido pedido) {
		String sql = "INSERT INTO pedido(id, id_usuario) VALUES (?,?);";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			
			pedido.setId(proximoID());
			statement.setLong(1, pedido.getId());
			statement.setLong(2, pedido.getId_usuario());
					
			statement.execute();
			for(Item item : pedido.getItens()) {
				item.setId_pedido(pedido.getId());
				
				livroDAO.subtrairLivros(item.getLivro().getId(), item.getQuant());
				itemDAO.adiciona(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;	
	}
	
	/*public boolean altera(Pedido pedido) {
		if(pedido.getId() == null) throw new IllegalStateException("Id do pedido não deve ser null");
		
		String sql = "UPDATE pedido SET titulo=?, autor=?, descricao=?, categoria=?, valor=? where id=?;";

		PreparedStatement statement;
		try { 
			statement = connection.prepareStatement(sql);
			statement.setString(1, pedido.getTitulo());
			statement.setString(2, pedido.getAutor());
			statement.setString(3, pedido.getDescricao());
			statement.setString(4, pedido.getCategoria());
			statement.setDouble(5, pedido.getValor());
			statement.setLong(6, pedido.getId());
			
			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean remove(Long id) {
		if(id == null) throw new IllegalStateException("Id do pedido não deve ser null");
				
		String sql = "DELETE FROM pedido WHERE id=?;";
		
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
		
	}*/
	
	/*public Pedido pesquisa(Long id) {
		if(id == null) throw new IllegalStateException("Id do pedido não deve ser null");
		
		String sql = "SELECT * FROM pedido WHERE id=?";
		Pedido pedido = new Pedido();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				pedido.setId(rs.getLong("id"));
				pedido.setTitulo(rs.getString("titulo"));
				pedido.setAutor(rs.getString("autor"));
				pedido.setDescricao(rs.getString("descricao"));
				pedido.setCategoria(rs.getString("categoria"));
				pedido.setValor(rs.getFloat("valor"));
			}
			
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return pedido;
	}
	*/
	
	public List<Pedido> listaTodos(){
		String sql = "SELECT * FROM pedido;";
		List<Pedido> pedidos = new ArrayList<>();
		ItemDAO itemDAO = new ItemDAO();
		
		try(PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery()){
			
			while(rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(rs.getLong("id"));
				pedido.setId_usuario(rs.getLong("id_usuario"));
				Usuario usuario = usuarioDAO.pesquisa(pedido.getId_usuario());
				pedido.setUsuario(usuario.getLogin());
				pedido.setItens(itemDAO.obtemItensPorPedido(pedido.getId()));
				
				float total = 0;
				for(Item item : pedido.getItens())
					total += item.getValor_total();
				
				pedido.setTotal(total);
				pedidos.add(pedido);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pedidos;
		
	}
	
	public Long proximoID() {
		String sql = "SELECT nextval('seq-usuario');";
		
		try(PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery()){
				
			if(rs.next())
				return rs.getLong("nextval");
			
		}catch (SQLException e) { }
		
		return null;
	}

	public List<Pedido> listaTodosDoUsuario(Long id_usuario) {
		String sql = "SELECT * FROM pedido WHERE id_usuario=?";
		List<Pedido> pedidos = new ArrayList<>();
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setLong(1, id_usuario);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(rs.getLong("id"));
				pedido.setId_usuario(rs.getLong("id_usuario"));

				Usuario usuario = usuarioDAO.pesquisa(id_usuario);
				pedido.setUsuario(usuario.getLogin());

				pedido.setItens(itemDAO.obtemItensPorPedido(pedido.getId()));

				float total = 0;
				for(Item item : pedido.getItens())
					total += item.getValor_total();

				pedido.setTotal(total);
				pedidos.add(pedido);
			}

		} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return pedidos;
	}
}
