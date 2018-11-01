package lhos.daw.livraria.model;

public class Livro {
	
	private Long id;
	private String titulo;
	private String autor;
	private String descricao;
	private String categoria;
	private float valor;
	private int quantidade;
	
	public Livro() {
		titulo = autor = descricao = categoria = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long codigo) {
		this.id = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", descricao=" + descricao
				+ ", categoria=" + categoria + ", valor=" + valor + "]";
	}

	
}
