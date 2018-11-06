package distribuidora.managebeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import distribuidora.dao.DAO;
import distribuidora.modelo.Produto;

@ViewScoped
@ManagedBean
public class ProdutoMB {

	private Produto produto = new Produto();
	private List<Produto> produtos = new ArrayList<>();
	
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public boolean gravar () {
		DAO<Produto> dao = new DAO<>(Produto.class);
		
		if (produto.getId() != null)
			return alterar();
		
		boolean result = dao.insert(getProduto());
		
		this.produto = new Produto();
		
		return result;
	}
	
	public boolean alterar() {
		DAO<Produto> dao = new DAO<>(Produto.class);
		
		boolean result = dao.update(getProduto());
		
		this.produto = new Produto();
		
		return result;
		
	}
	
	public boolean remove (Produto produto) {
		DAO<Produto> dao = new DAO<>(Produto.class);
		
		boolean result = dao.remove(produto);
		
		return result;
	}

	public List<Produto> getProdutos() {
		DAO<Produto> dao = new DAO<>(Produto.class);
		this.produtos = dao.list();
		
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
