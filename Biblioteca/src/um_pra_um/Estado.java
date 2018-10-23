package um_pra_um;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Estado {
	@Id
	@SequenceGenerator(name="gov_id", sequenceName="gov_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gov_id")
	private Long id;
	private String nome;
	
	@OneToOne(optional=false, cascade=CascadeType.PERSIST)
	private Governador governador;

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

	public Governador getGovernador() {
		return governador;
	}

	public void setGovernador(Governador governador) {
		this.governador = governador;
	}
}