package br.com.brytecnologia.desafio.backend.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tb_habitante")
public class Habitante implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5400464307826298516L;
	private String nome;

	@Id
	private String codigo;
	private List<Endereco> enderecos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
