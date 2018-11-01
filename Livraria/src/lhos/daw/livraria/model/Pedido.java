package lhos.daw.livraria.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	private Long id;
	private Long id_usuario;
	private String usuario;
	private List<Item> itens;
	private Float total;
	
	public Pedido() {
		itens = new ArrayList<Item>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", id_usuario=" + id_usuario + ", usuario=" + usuario + ", itens=" + itens
				+ ", total=" + total + "]";
	}

}
