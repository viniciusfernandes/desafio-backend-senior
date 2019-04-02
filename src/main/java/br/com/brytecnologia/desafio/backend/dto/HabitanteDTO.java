package br.com.brytecnologia.desafio.backend.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilizada para encapsular os dados envolvidos no trafego de informacao
 * entre o a camada de controller e a de servico.
 * 
 * @author vinic
 *
 */
public class HabitanteDTO {
	private String nome;

	private String codigo;

	private List<EnderecoDTO> enderecos;

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

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	public boolean hasEndereco() {
		return enderecos != null && !enderecos.isEmpty();
	}

	public void clearEnderecos() {
		setEnderecos(new ArrayList<>());
	}

	public void addEndereco(EnderecoDTO endereco) {
		if (enderecos == null) {
			setEnderecos(new ArrayList<>());
		}
		enderecos.add(endereco);
	}
}
