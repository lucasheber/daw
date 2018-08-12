package br.edu.barbacena.ifsudestemg.daw.modelo;

public class Professor {
	
	private Long id;
	private String nome;
	private String email;
	private String grauFormacao;
	
	public Professor() { }

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

	public String getGrauFormacao() {
		return grauFormacao;
	}

	public void setGrauFormacao(String grauFormacao) {
		this.grauFormacao = grauFormacao;
	}
	
	@Override
	public String toString() {
		return String.format( "%04d. %s", id, nome );
	}
	
}// class Professor
