package distribuidora.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

public class NotaFiscal {
	
	@Id
	@SequenceGenerator(name="notafiscal_id", sequenceName="seq_nf", initialValue=1)
	@GeneratedValue(generator="notafiscal_id", strategy=GenerationType.SEQUENCE)
	private Long id;
	private String cnpj;
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="notaFiscal")
	private List<Item> itens = new ArrayList<>();
	
	@Transient
	private int naoPersite;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public int getNaoPersite() {
		return naoPersite;
	}

	public void setNaoPersite(int naoPersite) {
		this.naoPersite = naoPersite;
	}
}
