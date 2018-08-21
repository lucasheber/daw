package br.edu.ifsudestemg.barbacena.agendaweb.model;

import java.util.Calendar;

public class Contato {
	private long id;
	private String nome, email, endereco, telefone;
	private Calendar dataNascimento;
	
	public Contato() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return String.format( "%s, %s, %s, %s, %s", nome, email, endereco, telefone, dateFormatBR(dataNascimento) );
	}
	

	/**
	 * Retorna a data no formato DD/MM/YYYY
	 * 
	 * @param data <code>Calendar</code> a data a ser convertida.
	 * 
	 * @return <code>String</code> da data convertida.
	 */
	public String dateFormatBR( Calendar data ) {
		
		String dataBR = "";
		dataBR += String.format("%02d", data.get(Calendar.DAY_OF_MONTH) );
		dataBR += "/" +  String.format("%02d", data.get(Calendar.MONTH) + 1);
		dataBR += "/" +  data.get(Calendar.YEAR);
		
		return dataBR;
	}
	
}
