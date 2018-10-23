package jcms.daw.jpa.biblioteca.modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Livro {
	@Id
	@SequenceGenerator(name="livro_id", sequenceName = "livro_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="livro_id")
	private Long id;
	
	private String isbn;
	
	@Column (length=30, unique=true)
	private String nome;
	
	// Indica que o valor terá 3 digitos, sendo 2 deles referentes às casas decimais.
	@Column (precision =3, scale =2)
	private double preco;
	
	@Temporal (TemporalType.DATE)
	private Calendar datalancamento;
	
	// Faz com o que o atributo não seja armazenado no banco de dados.
	@Transient
	private int count;
	
	
	public static int teste = 0;
	

	public Livro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Calendar getDatalancamento() {
		return datalancamento;
	}

	public void setDatalancamento(Calendar datalancamento) {
		this.datalancamento = datalancamento;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static int getTeste() {
		return teste;
	}

	public static void setTeste(int teste) {
		Livro.teste = teste;
	}
	
	
	
	
	
}
