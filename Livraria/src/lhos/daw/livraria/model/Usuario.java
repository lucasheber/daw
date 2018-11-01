package lhos.daw.livraria.model;

public class Usuario {
	
	private Long id;
	private String login;
	private String senha;
	private String perfil;
	
	public Usuario() {
		login = senha = "";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + "]";
	}

	
}
