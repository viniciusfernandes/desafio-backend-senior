package br.com.brytecnologia.desafio.backend.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8248563394094551454L;

	private String bairro;

	private String codigoPostal;

	private String complemento;
	// O fetch type deve ser Lazy para se evitar carregamentos desnecessarios de
	// dados, o que pode acarretar em lentidao do sistema
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_habitante", referencedColumnName = "codigo", nullable = false)
	private Habitante habitante;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String localidade;
	private String logradouro;
	private Integer numero;
	private String uf;

	public String getBairro() {
		return bairro;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public String getComplemento() {
		return complemento;
	}

	public Habitante getHabitante() {
		return habitante;
	}

	public Integer getId() {
		return id;
	}

	public String getLocalidade() {
		return localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getUf() {
		return uf;
	}

	public boolean hasCodigoPostal() {
		return codigoPostal != null && codigoPostal.trim().length() > 0;
	}

	public boolean hasLogradouro() {
		return logradouro != null && logradouro.trim().length() > 0;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setHabitante(Habitante habitante) {
		this.habitante = habitante;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
