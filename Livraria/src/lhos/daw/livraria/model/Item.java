package lhos.daw.livraria.model;

public class Item {
	
	private Long id_pedido;
	private Livro livro;
	private float valor;
	private int quant;
	private float valor_total;
	
	public Item() {
		livro = new Livro();
	}

	public Long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Long id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public Float getValor_total() {
		return valor_total;
	}

	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}

	@Override
	public String toString() {
		return "Item [id_pedido=" + id_pedido + ", livro=" + livro + ", valor=" + valor + ", quant=" + quant
				+ ", valor_total=" + valor_total + "]";
	}
	
}
