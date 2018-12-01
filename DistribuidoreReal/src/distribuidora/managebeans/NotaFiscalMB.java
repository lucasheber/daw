package distribuidora.managebeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import distribuidora.dao.DAO;
import distribuidora.modelo.Item;
import distribuidora.modelo.NotaFiscal;
import distribuidora.modelo.Produto;

@ManagedBean
@ViewScoped
public class NotaFiscalMB {

	private NotaFiscal notaFiscal = new NotaFiscal();
	private Item item = new Item();
	private Long idProduto;
	
	public void guardaItem () {
		DAO<Produto> dao = new DAO<>(Produto.class);
		Produto produto = dao.findById(idProduto);
		
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		item.setNotaFiscal(notaFiscal);
		
		notaFiscal.getItens().add(item);
		
		item = new Item();
	}
	
	public void gravar () {
		DAO<NotaFiscal> dao = new DAO<>(NotaFiscal.class);
		
		dao.insert(notaFiscal);
		notaFiscal = new NotaFiscal();
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public List<NotaFiscal> getNotas() {
		return new DAO<>(NotaFiscal.class).list();
	}
}
