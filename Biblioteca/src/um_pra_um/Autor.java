package um_pra_um;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Autor {

	@Id
	@SequenceGenerator(name="autor_id", sequenceName="autor_seq", allocationSize=1)
	@GeneratedValue(generator="autor_id", strategy=GenerationType.SEQUENCE)
	private Long id;
	private String nome;
	

//	private List<Livro> livros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public List<Livro> getLivros() {
//		return livros;
//	}
//
//	public void setLivros(List<Livro> livros) {
//		this.livros = livros;
//	}
	
	
}
