package lhos.daw.livraria.model;

public class Link {
	private String rotulo;
	private String acao;
	
	public Link(String acao, String rotulo) {
		this.rotulo = rotulo;
		this.acao = acao;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
	
}
