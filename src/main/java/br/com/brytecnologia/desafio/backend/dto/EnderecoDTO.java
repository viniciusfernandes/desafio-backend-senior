package br.com.brytecnologia.desafio.backend.dto;

import java.io.Serializable;

/**
 * Classe utilizada para encapsular os dados envolvidos no trafego de informacao
 * entre o a camada de controller e a de servico.
 * 
 * @author vinic
 *
 */
public class EnderecoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4049646103778661917L;
	private String bairro;
	private String codigoPostal;
	private String complemento;
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

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
