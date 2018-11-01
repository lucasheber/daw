package lhos.daw.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lhos.daw.livraria.dao.connection.ConnectionFactory;
import lhos.daw.livraria.model.Item;

public class ItemDAO {
	
	private Connection connection;

	public ItemDAO () {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public boolean adiciona(Item Item) {
		String sql = "INSERT INTO itens_pedido(id_pedido, id_produto, quant, valor, valor_total) VALUES (?,?,?,?,?);";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setLong(1, Item.getId_pedido());
			statement.setLong(2, Item.getLivro().getId());
			statement.setInt(3, Item.getQuant());
			statement.setFloat(4, Item.getValor());
			statement.setFloat(5, Item.getValor_total());
					
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;	
	}

	public List<Item> obtemItensPorPedido(Long id_pedido) {
		if(id_pedido == null) throw new IllegalStateException("Id do Item não deve ser null");

		String sql = "SELECT * FROM itens_pedido WHERE id_pedido=?";
		List<Item> itens = new ArrayList<>();
		LivroDAO livroDAO = new LivroDAO();
		

		try(PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setLong(1, id_pedido);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				Item item = new Item();
				
				item.setId_pedido(rs.getLong("id_pedido"));
				item.setQuant(rs.getInt("quant"));
				item.setValor(rs.getFloat("valor"));
				item.setValor_total(rs.getFloat("valor_total"));
				item.setLivro(livroDAO.pesquisa(rs.getLong("id_produto")));
				itens.add(item);
			}

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return itens;
	}

}
