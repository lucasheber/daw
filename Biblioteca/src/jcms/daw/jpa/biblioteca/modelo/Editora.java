package jcms.daw.jpa.biblioteca.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Editora {
	@Id
	@SequenceGenerator(name="editora_id", sequenceName="editora_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="editora_id")
	private Long id;
	private String nome, email;
	
	public Editora() {
	}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Nome : %s, Email : %s", nome, email);
	}
	
	
	
	
}
