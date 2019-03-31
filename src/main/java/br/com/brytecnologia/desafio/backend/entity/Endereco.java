package br.com.brytecnologia.desafio.backend.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tb_usuario")
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8248563394094551454L;

	@Id
	private Integer id;

	private Habitante habitante;
	private String bairro;
	private String logradouro;
	private String cep;
	private Integer numero;
	private String complemento;
	private String localizacao;
	private String uf;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Habitante getHabitante() {
		return habitante;
	}

	public void setHabitante(Habitante habitante) {
		this.habitante = habitante;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
